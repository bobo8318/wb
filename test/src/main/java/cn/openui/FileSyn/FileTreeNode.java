package cn.openui.FileSyn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTreeNode {

	public final static int NODE_TYPE_THIEF = 0;
	public final static int NODE_TYPE_NODE = 0;
	
	public String name;
	public long values;
	public File file;
	public int type;
	
	private List<FileTreeNode> children = null;
	
	public void addChild(FileTreeNode child){
		if(children == null)
			children = new ArrayList<FileTreeNode>();
		
		children.add(child);
	}
	
	public int getChildSize(){
		int result = children==null?0:children.size();
		return result;
	}
	
	public FileTreeNode getChild(int index){
		if(index<0||index>getChildSize()){
			return null;
		}else{
			return children.get(index);
		}
		
	}
	
	public void show(){
		System.out.println(name+"--"+values);
	}
	
}
