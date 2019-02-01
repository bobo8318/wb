package cn.openui.excelHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ExcelHelper<T> {
	
	
	private final int EXCEL_TYPE_2003 = 0;
	private final int EXCEL_TYPE_2007 = 1;
	private final int EXCEL_TYPE_CSV = 2;
	
	private int excel_type;
	
	private XSSFWorkbook wb2007;
	private XSSFSheet sheet2007;
	
	private HSSFWorkbook wb2003;
	private HSSFSheet sheet2003;
	private ExcelDataAdapterInterface adapter;
	
	
	private BufferedReader br = null;
	public String CSV_SPLIT = "	";
	
	
	List<List<String>> resultdata;
	
	private DataHandler handler;
	
	private int[] clumnsRule;
	
	
	private int titleStart = 0;
	private int colEnd = -1;
	private int rowEnd = -1;
	
	public void setSplit(String split){
		this.CSV_SPLIT = split;
	}
	
	public ExcelHelper(){
		
	}
	

	public void setTitleStart(int titleStart) {
		this.titleStart = titleStart;
	}
	public void setColEnd(int colEnd) {
		this.colEnd = colEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public  int[] getClumnsRule() {
		return this.clumnsRule;
	}

	public  void setClumnsRule(int[] clumnsRule) {
		this.clumnsRule = clumnsRule;
	}

	public void setDataHandler(DataHandler handler){
		this.handler = handler;
	}
	
	public void exportExcel() throws IOException{
		if(adapter!=null){
			for(int i=0;i<adapter.getYSize();i++){//每行
				XSSFRow newRow = sheet2007.createRow(adapter.getYIndex()+i);
				
				//插入序号cell
				XSSFCell newNoCell = newRow.createCell(0,Cell.CELL_TYPE_STRING);
				newNoCell.setCellValue(i+1);
				//具体内容cell
				for(int j=1;j<adapter.getXSize();j++){//每格
					XSSFCell newCell = newRow.createCell(j,Cell.CELL_TYPE_STRING);
					String data = adapter.getData(j, i);
					//System.out.println("");
					newCell.setCellValue(data);
				}
			}
			storeFile("D:/template.xlsx");
		}
	}
	
	public List<String[]> importData() throws IOException, ParserConfigurationException, SAXException, OpenXML4JException {
		 List<String[]> result = new ArrayList<String[]>();
			if(excel_type == EXCEL_TYPE_2003){
				HSSFRow row = null;
				int colNum = 0;
				int yCount = sheet2003.getPhysicalNumberOfRows();
				if(yCount>0)
					colNum = sheet2003.getRow(this.titleStart).getPhysicalNumberOfCells();
				if(this.colEnd>0){
					colNum = this.colEnd;
				}
				
				//System.out.println("---------------------"+this.titleStart);
				
				for(int i=this.titleStart+1;i<yCount;i++){
					row = sheet2003.getRow(i);
					String[] lineData = new String[colNum];
					//StringBuffer sb = new StringBuffer();
					int count = 0;
					for(int j=0;j<colNum;j++){
						//System.out.println(i+" "+colNum+"-"+this.colEnd);
						HSSFCell cell = row.getCell(j);
						String value = this.get2003CellValue(cell);
						
						lineData[j] = value;
						//if(j>0)sb.append(this.CSV_SPLIT);
						//sb.append(value);
						count += value.length();
					}
					if(count==0) break;
					//System.out.println(lineData.length);
					result.add(lineData);
					//System.out.println(result.get(0).length);
				}
				
			}else if(excel_type == EXCEL_TYPE_CSV){
				if(br!=null){
					String line;
					int i = 0;
					while((line=br.readLine())!=null){
						//String[] linedata = ;
						//String[] linedata =line.split(this.CSV_SPLIT);
						
						result.add(line.split(this.CSV_SPLIT));
						//result.add(this.CSV_SPLIT);
					}
					this.br.close();
				}
			}else if(excel_type == EXCEL_TYPE_2007){
				this.resultdata = this.process();
			}
			
		return result;
	}
	
	public int initSheet(String template) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
		
		
		if(template.indexOf(".xlsx")>=0){//2007
			FileInputStream excelFileInoutStream = new FileInputStream(template);
			wb2007 = new XSSFWorkbook(excelFileInoutStream);
			sheet2007 = wb2007.getSheetAt(0);
			excelFileInoutStream.close();

			excel_type = this.EXCEL_TYPE_2007;

			File excelFile = new File(template);
			this.xlsxPackage = OPCPackage.open(excelFile.getPath(), PackageAccess.READ);

			return 1;
		}else if(template.indexOf(".xls")>=0){//2003
			FileInputStream excelFileInoutStream = new FileInputStream(template);
			excel_type = this.EXCEL_TYPE_2003;
			wb2003 = new HSSFWorkbook(excelFileInoutStream);
			sheet2003 = wb2003.getSheetAt(0);
			excelFileInoutStream.close();
			return 1;
		}else if(template.contains("csv")){
			excel_type = EXCEL_TYPE_CSV;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(template),"UTF-8"));
			return 1;
		}else{
			return 0;
		}

	}
	
	public void storeFile(String name) throws IOException{
		FileOutputStream excelFile = new FileOutputStream(name);
		
		if(excel_type == this.EXCEL_TYPE_2003)
			wb2003.write(excelFile);
		if(excel_type == this.EXCEL_TYPE_2007)
			wb2007.write(excelFile);
		
		excelFile.close();
	}
	
	public void setAdapter(ExcelDataAdapterInterface adapter){
		this.adapter = adapter;
	}
	
	/**
	 * 读取excel文件 获取第一行标题行
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws OpenXML4JException 
	 */
	public String[] getTitles() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
		if(excel_type == this.EXCEL_TYPE_2003){
			HSSFRow row = sheet2003.getRow(this.titleStart);
			int colNum = row.getPhysicalNumberOfCells();
			if(this.colEnd>0)
				colNum = colEnd;
			//System.out.println("getTitles colNum:"+colNum);
			String[] titles = new String[colNum];
			for(int i=0;i<colNum;i++){
				if(row.getCell(i)!=null)
					titles[i] = this.get2003CellValue(row.getCell(i));
				else
					titles[i] = "";
				//System.out.println("2003 title:"+titles[i]);
			}
			return titles;
		}else if(excel_type == this.EXCEL_TYPE_2007){
			XSSFRow row = sheet2007.getRow(this.titleStart);
			int colNum = row.getPhysicalNumberOfCells();
			if(this.colEnd>0)
				colNum = colEnd;
			//System.out.println("getTitles colNum:"+colNum);
			String[] titles = new String[colNum];
			for(int i=0;i<colNum;i++){
				if(row.getCell(i)!=null)
					titles[i] = this.get2007CellValue(row.getCell(i));
				else
					titles[i] = "";
				//System.out.println("2003 title:"+titles[i]);
			}
			return titles;


			//return this.titleLine;
		}else if(excel_type == this.EXCEL_TYPE_CSV){
			if(br!=null){
				String line;
				if((line=br.readLine())!=null){
					String[] titles = line.split(this.CSV_SPLIT);
					this.br.close();
					return titles;
				}
				
			}
		}
		return null;
	}
	/***
	 * 根据excel表中所有数据 读出最大该列数据最大长度
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws OpenXML4JException 
	 */

	public int[] getLength() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		if(excel_type == this.EXCEL_TYPE_2003){
			
			HSSFRow row = sheet2003.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			int[] colLength = new int[colNum];
			for(int i=0;i<colLength.length;i++){
				colLength[i] = 10;
			}
			
			int rows = sheet2003.getPhysicalNumberOfRows();
			for(int rowIndex = 1;rowIndex<rows;rowIndex++){
				HSSFRow datarow = sheet2003.getRow(rowIndex);
				for(int i=0;i<colNum;i++){
					String value = this.get2003CellValue(datarow.getCell(i));
							//datarow.getCell(i)==null?"":datarow.getCell(i).getStringCellValue();
					int dataLength = value.length();
					if(colLength[i] < dataLength)
						colLength[i] = dataLength;
					//System.out.println("2003 title:"+titles[i]);
				}
			}
			return colLength;
		}else if(excel_type == this.EXCEL_TYPE_2007){
			return this.lengthList;

		}else if(excel_type == this.EXCEL_TYPE_CSV){
			int[] colLength = null;
			if(br!=null){
				String line;
				int count = 0;
				while((line=br.readLine())!=null){
					String[] titles = line.split(this.CSV_SPLIT);
					if(count==0){//title
						colLength = new int[titles.length];
					}else{//data
						for(int i=0;i<titles.length;i++){
							int dataLength = titles[i].length();
							if(colLength[i] < dataLength)
								colLength[i] = dataLength;
						}					
					}
					count++;
					this.br.close();
					
				}
				
			}
			return colLength;
		}
		return null;
	}
		
	private String get2003CellValue(HSSFCell cell){
			String value = "";
			if(cell!=null){
				int cellType = cell.getCellType();
				//System.out.println(cellType);
				switch(cellType){
					case HSSFCell.CELL_TYPE_STRING:value = cell.getStringCellValue();break;
					case HSSFCell.CELL_TYPE_NUMERIC://日期时间格式转换
						value = ""+cell.getNumericCellValue();
						break;
					default:value = cell.getStringCellValue() ;
				}

					short format = cell.getCellStyle().getDataFormat();
					if(format==14 || format==31 || format==57 || format==58){
						try{
							Double datavalue = Double.valueOf(value);
							Date date = DateUtil.getJavaDate(datavalue);
							//value = DateTools.getDate(date, "yyyy-MM-dd");
						}catch(Exception e){
							;
						}
					}
		}
		return value;
	}
	
	private String get2007CellValue(XSSFCell cell){
		String value = "";
		if(cell!=null){
			int cellType = cell.getCellType();
			switch(cellType){
				case XSSFCell.CELL_TYPE_STRING:value = cell.getStringCellValue();break;
				case XSSFCell.CELL_TYPE_NUMERIC://日期时间格式转换
					value = ""+cell.getNumericCellValue();
					break;
				default:value = cell.getStringCellValue() ;
			}
			
			
			
				short format = cell.getCellStyle().getDataFormat();
				if(format==14 || format==31 || format==57 || format==58){
					try{
						Double datavalue = Double.valueOf(value);
						Date date = DateUtil.getJavaDate(datavalue);
						//value = DateTools.getDate(date, "yyyy-MM-dd");
					}catch(Exception e){
						;
					}
				}
		}
		return value;
	}
		
	/********************************************************************************************/
	// The type of the data value is indicated by an attribute on the cell.
		// The value is usually in a "v" element within the cell.
		enum xssfDataType {
			BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
		}

		

		/****
		 * 
		 * @author Administrator
		 *
		 */
		

		// //////////////////////////////////////////////////////////////////////////////////////////////////

		private OPCPackage xlsxPackage;
		private int minColumns;
		private List<List<String>> resultstore = new ArrayList<List<String>>();
		private String[] titleLine;
		private String[] tableColumnName;
		private int[] lengthList;

		/*SQLHelper sqlhelper = new SQLHelper();
		
		public void setDataSource(QbDao dao){
			sqlhelper.setDao(dao);
		}*/

		// Creates a new XLSX -> CSV converter
		// @param pkg The XLSX package to process
		// @param output The PrintStream to output the CSV to
		// @param minColumns The minimum number of columns to output, or -1 for no
		// minimum
		public void setMInColumns(int mincol){
			this.minColumns = mincol;
		}

		// Parses and shows the content of one sheet
		// using the specified styles and shared-strings tables.
		// @param styles
		// @param strings
		// @param sheetInputStream
		public List<List<String>> processSheet(StylesTable styles,ReadOnlySharedStringsTable strings, InputStream sheetInputStream)
				throws IOException, ParserConfigurationException, SAXException {

			InputSource sheetSource = new InputSource(sheetInputStream);
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxFactory.newSAXParser();
			XMLReader sheetParser = saxParser.getXMLReader();

			MyXSSFSheetHandler sheethandler = new MyXSSFSheetHandler(styles, strings, this.minColumns, 50000);
			sheethandler.setHandler(this.handler);

			sheetParser.setContentHandler(sheethandler);
			sheetParser.parse(sheetSource);

			return sheethandler.getData();
		}

		// Initiates the processing of the XLS workbook file to CSV.
		// @throws IOException
		// @throws OpenXML4JException
		// @throws ParserConfigurationException
		// @throws SAXException

		public List<List<String>> process() throws IOException, OpenXML4JException,ParserConfigurationException, SAXException {

			ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
			XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);

			StylesTable styles = xssfReader.getStylesTable();
			XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
			//int index = 0;
			if (iter.hasNext()) {//每个sheet
				InputStream stream = iter.next();
				//String sheetName = iter.getSheetName();
				//this.output.println();
				List<List<String>> result = this.processSheet(styles, strings, stream);
				stream.close();
				return result;
				//++index;
			}
			return null;
		}
		
		/****
		 * 
		 * @author 数据处理接口
		 *
		 */
		public interface DataHandler{
			public void handler(List data, int status);//处理数据
		} 
		
}
