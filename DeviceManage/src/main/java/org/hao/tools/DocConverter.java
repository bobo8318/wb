package main.java.org.hao.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocConverter {
	private static final int environment = 1;// 环境 1：windows 2:linux
	private String fileString;// (只涉及pdf2swf路径问题)
	private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;

	public DocConverter(String fileString) {
		ini(fileString);
	}

	/**
	 * 重新设置file
	 * 
	 * @param fileString
	 */
	public void setFile(String fileString) {
		ini(fileString);
	}

	/**
	 * 初始化
	 * 
	 * @param fileString
	 */
	private void ini(String fileString) {
		this.fileString = fileString;
		fileName = fileString.substring(fileString.lastIndexOf("/"), fileString.lastIndexOf("."));
		docFile = new File(fileString);
		System.out.println("*****doc:****"+docFile.getName());
		pdfFile = new File(Parameter.DOC_SAVE_URL,fileName + ".pdf");
		System.out.println("*****pdf:****"+pdfFile.getName());
		String fileTimeName = CommonTools.FileRenameByTime();
		if(fileTimeName!=null&&!"".equals(fileTimeName))
			swfFile = new File(Parameter.SWF_SAVE_URL,fileTimeName + ".swf");
		else
			swfFile = new File(Parameter.SWF_SAVE_URL,fileName + ".swf");
		System.out.println("*****swf:****"+swfFile.getName());
	}

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(
						8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(
							connection);
					System.out.println("****开始转换pdf文件" + pdfFile.getName()
							+ "****");
					converter.convert(docFile, pdfFile);
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()
							+ "****");
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}

	/**
	 * 转换成 swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (getEnvironment() == 1) {// windows环境处理
					try {
						Process p = r
								.exec("C:/Program Files/SWFTools/pdf2swf.exe "
										+ pdfFile.getPath() + " -o "
										+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (getEnvironment() == 2) {// linux环境处理
					try {
						Process p = r.exec("pdf2swf " + pdfFile.getPath()
								+ " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				System.out.println("****pdf不存在,无法转换****");
			}
		} else {
			System.out.println("****swf已经存在不需要转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException {

		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();

		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}

		return buffer.toString();
	}

	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public boolean conver() {

		if (swfFile.exists()) {
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			return true;
		}

		if (getEnvironment() == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}
		try {
			String filetype = fileString.substring(fileString.lastIndexOf(".")+1,fileString.length());
			System.out.println("****filetype****："+filetype);
			if("doc".equalsIgnoreCase(filetype)||"ppt".equalsIgnoreCase(filetype)){
				System.out.println("****转换doc、ppt文件****");
				doc2pdf();
				pdf2swf();
			}else if("pdf".equalsIgnoreCase(filetype)){
				System.out.println("****转换pdf文件****");
				pdf2swf();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}

	}

	/**
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"),
					fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}

	public static int getEnvironment() {
		return environment;
	}

}
