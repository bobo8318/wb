package cn.openui.excelHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import cn.openui.excelHelper.ExcelHelper.DataHandler;
import cn.openui.excelHelper.ExcelHelper.xssfDataType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/***
 * 处理每个sheet表格数据 
 * @author Administrator
 *
 */
public class MyXSSFSheetHandler extends DefaultHandler{


	// Table with styles
	private StylesTable stylesTable;

	// Table with unique strings
	private ReadOnlySharedStringsTable sharedStringsTable;

	// Destination for data
	private  List<List<String>> handlerstore = null;
	private  List<String> linedata = null;
	

	// Number of columns to read starting with leftmost

	// Set when V start element is seen
	private boolean vIsOpen;

	// Set when cell start element is seen;
	// used when cell close element is seen.
	private xssfDataType nextDataType;

	// Used to format numeric cell values.
	private short formatIndex;
	private String formatString;
	private final DataFormatter formatter;
	
	private int thisColumn = -1;
	// The last column printed to the output stream
	private int lastColumnNumber = -1;

	// Gathers characters as they are seen.
	private StringBuffer value;
	
	private DataHandler dataHandler = null;//处理每行数据
	
	public void setHandler(DataHandler dataHandler){
		this.dataHandler = dataHandler;
	}
	
	private int upgroup = 1;//多少条数据提交一次
	
	private int countrows = 0;
	private int minColumns;

	// Accepts objects needed while parsing.
	// @param styles Table of styles
	// @param strings Table of shared strings
	// @param cols Minimum number of columns to show
	// @param target Sink for output
	public MyXSSFSheetHandler(StylesTable styles,ReadOnlySharedStringsTable strings, int cols, int upgroup) {
		this.stylesTable = styles;
		this.sharedStringsTable = strings;
		this.minColumns = cols;
		//this.handlerstore = store;
		this.value = new StringBuffer();
		this.nextDataType = xssfDataType.NUMBER;
		this.formatter = new DataFormatter();
		
		this.upgroup = upgroup<=0?1:upgroup;
	}

	// @see
	// org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	// java.lang.String, java.lang.String, org.xml.sax.Attributes)
	public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		if("c".equals(name)){
			value.setLength(0);
		}
		if ("inlineStr".equals(name) || "v".equals(name)) {
			vIsOpen = true;
			// Clear contents cache
			value.setLength(0);
		}
		// c => cell
		else if ("c".equals(name)) {
			// Get the cell reference
			
			String r = attributes.getValue("r");
			//System.out.println("r:"+r);
			int firstDigit = -1;
			for (int c = 0; c < r.length(); ++c) {
				if (Character.isDigit(r.charAt(c))) {
					firstDigit = c;
					break;
				}
			}
			thisColumn = nameToColumn(r.substring(0, firstDigit));
			
			// Set up defaults.
			this.nextDataType = xssfDataType.NUMBER;
			this.formatIndex = -1;
			this.formatString = null;
			String cellType = attributes.getValue("t");
			String cellStyleStr = attributes.getValue("s");
			if ("b".equals(cellType))
				nextDataType = xssfDataType.BOOL;
			else if ("e".equals(cellType))
				nextDataType = xssfDataType.ERROR;
			else if ("inlineStr".equals(cellType))
				nextDataType = xssfDataType.INLINESTR;
			else if ("s".equals(cellType))
				nextDataType = xssfDataType.SSTINDEX;
			else if ("str".equals(cellType))
				nextDataType = xssfDataType.FORMULA;
			else if (cellStyleStr != null) {
				// It's a number, but almost certainly one
				// with a special style or format
				int styleIndex = Integer.parseInt(cellStyleStr);
				XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
				this.formatIndex = style.getDataFormat();
				this.formatString = style.getDataFormatString();
				if (this.formatString == null)
					this.formatString = BuiltinFormats
							.getBuiltinFormat(this.formatIndex);
			}
		}

	}

	// @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	// java.lang.String, java.lang.String)
	public void endElement(String uri, String localName, String name)throws SAXException {
		String thisStr = "";
		//System.out.println(name);
		if(linedata==null){
			linedata = new ArrayList<String>();
		}
		if ("c".equals(name)){//空值
			if(value.length()==0){					
				linedata.add("");
			}
			
		}else if ("v".equals(name)) {// v => contents of a cell
			// Process the value contents as required.
			// Do now, as characters() may be called more than once
			switch (nextDataType) {

			case BOOL:
				char first = value.charAt(0);
				thisStr = first == '0' ? "FALSE" : "TRUE";
				break;

			case ERROR:
				thisStr = "\"ERROR:" + value.toString() + '"';
				break;

			case FORMULA:
				// A formula could result in a string value,
				// so always add double-quote characters.
				thisStr = '"' + value.toString() + '"';
				break;

			case INLINESTR:
				// TODO: have seen an example of this, so it's untested.
				XSSFRichTextString rtsi = new XSSFRichTextString(
						value.toString());
				thisStr =  rtsi.toString();
				break;

			case SSTINDEX:
				String sstIndex = value.toString();
				try {
					int idx = Integer.parseInt(sstIndex);
					XSSFRichTextString rtss = new XSSFRichTextString(
							sharedStringsTable.getEntryAt(idx));
					thisStr =  rtss.toString();
				} catch (NumberFormatException ex) {
					System.out.println("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
				}
				break;

			case NUMBER:
				String n = value.toString();
				if (this.formatString != null)
					thisStr = formatter.formatRawCellContents(
							Double.parseDouble(n), this.formatIndex,
							this.formatString);
				else
					thisStr = n;
				break;

			default:
				thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
				break;
			}
			
			//System.out.println("*******************************thisStr:"+thisStr);
			// Output after we've seen the string contents
			// Emit commas for any fields that were missing on this row
			if (lastColumnNumber == -1) {
				lastColumnNumber = 0;
			}
			/*for (int i = lastColumnNumber; i < thisColumn; ++i) {
				output.print(',');
			}*/

			// Might be the empty string.
			
			linedata.add(thisStr);

			// Update column
			if (thisColumn > -1) {
				lastColumnNumber = thisColumn;
			}

		} else if ("row".equals(name)) {

			// Print out any missing commas if needed
			if (minColumns > 0) {
				// Columns are 0 based
				if (lastColumnNumber == -1) {
					lastColumnNumber = 0;
				}
				/*for (int i = lastColumnNumber; i < (this.minColumnCount); i++) {
					output.print(',');
				}*/
			}

			// We're onto a new row

			//output.println();
			//System.out.println("countrows:"+countrows);
			//*************************************************************
			countrows++;
			if(handlerstore == null){
				handlerstore = new ArrayList<List<String>>();
			}
			handlerstore.add(linedata);
			
			//System.out.println("linedata:"+linedata.get(5));
			linedata = new ArrayList<String>();
			if(countrows%this.upgroup==0){
				System.out.println("rows:"+countrows);
			}
			
			if(handlerstore.size()>=this.upgroup){//每10万条数据清空一次list 进行一次数据操作
				//数据处理
				if(this.dataHandler !=null){
					//System.out.println("dataHandler:"+handlerstore.size());
					dataHandler.handler(handlerstore,(int)(countrows/this.upgroup));
				}
			}
			//linedata = new ArrayList<String>();
			lastColumnNumber = -1;

		}else if("sheetData".equals(name)){//结束
			if(this.dataHandler !=null){
				//System.out.println("dataHandler:"+handlerstore.size());
				dataHandler.handler(handlerstore,1);
			}
		}
		
	}

	// Captures characters only if a suitable element is open.
	// Originally was just "v"; extended for inlineStr also.
	public void characters(char[] ch, int start, int length)throws SAXException {
		if (vIsOpen)
			value.append(ch, start, length);
		
	}

	// Converts an Excel column name like "C" to a zero-based index.
	// @param name
	// @return Index corresponding to the specified name
	private int nameToColumn(String name) {
		int column = -1;
		for (int i = 0; i < name.length(); ++i) {
			int c = name.charAt(i);
			column = (column + 1) * 26 + c - 'A';
		}
		
		return column;
	}
	
	public List<List<String>> getData(){
		return handlerstore;
	}
}
