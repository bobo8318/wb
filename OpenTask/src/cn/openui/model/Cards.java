package cn.openui.model;

public class Cards {

	private String name;
	private String serial;
	private String desc;
	
	public Cards(String name, String serial, String desc){
		this.name = name;
		this.serial = serial;
		this.desc = desc;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
