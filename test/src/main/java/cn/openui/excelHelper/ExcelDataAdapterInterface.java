package cn.openui.excelHelper;

import java.util.List;

public interface ExcelDataAdapterInterface {

	//export
	public int getXSize();
	public int getYSize();
	
	public int getXIndex();
	public int getYIndex();
	
	public <T> T getData(int x, int y);
	public String bindData(int x, int y);
	
	public String getColumn(int x);
	public void setData(List data);
	
	public boolean OrderNumber();
	//import
}
