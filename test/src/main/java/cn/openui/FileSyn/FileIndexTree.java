package cn.openui.FileSyn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileIndexTree {
	
	public FileTreeNode root;
	public File dir;
	
	
	public FileIndexTree(String dir) {
		// TODO Auto-generated constructor stub
		this.dir = new File(dir);
	}
	/**
	 * 存储树
	 */
	public void storeIndex(String data){
		
	}
	/**
	 * 读取树
	 * @throws IOException 
	 */
	public void readIndex(String data) throws IOException{
		File param = new File(data);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(param)));
		String line = "";
		while((line = reader.readLine())!=null){
			
		}
	}
	
	public FileTreeNode buildTree(File root){
		FileTreeNode node = new FileTreeNode();
		node.type = FileTreeNode.NODE_TYPE_NODE;
		node.name = root.getName();
		node.values = 0;
		
		if(root.isDirectory()) {
			File[] childrenFile = root.listFiles();
			
			for(int i=0;i<childrenFile.length;i++){
				
				File file = childrenFile[i];
				if(file.isFile()){//如果是文件
					node.values = Math.max(file.lastModified(), node.values);
					//生成文件节点
					FileTreeNode filenode = new FileTreeNode();
					filenode.values = file.lastModified();
					filenode.file = file;
					filenode.type = FileTreeNode.NODE_TYPE_THIEF;
					filenode.name = file.getName();
					//filenode.show();
					node.addChild(filenode);
				}else if(file.isDirectory()){//如果是目录
					FileTreeNode filenode = buildTree(file);
					node.addChild(filenode);
					//filenode.show();
					node.values = Math.max(filenode.values, node.values);
				}
			}
		}
		
		return node;
	}
	
	public String compairTree(){
		return null;
	}
}
