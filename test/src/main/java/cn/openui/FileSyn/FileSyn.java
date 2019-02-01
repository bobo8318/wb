package cn.openui.FileSyn;

import java.io.File;

public abstract class FileSyn {

	
	private FileIndexTree fileTree;//文件哈夫曼树
	private int SYN_MODE = 1;//同步模式
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSyn syn = new FileSyn(){

			@Override
			public void doFile(File file) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		String dir = "E://mail";
		syn.start(dir);
	}
	
	private void start(String dir) {
		// TODO Auto-generated method stub
		//检索文件目录生成索引树
		fileTree = new FileIndexTree(dir);
		fileTree.root = fileTree.buildTree(fileTree.dir);
		fileTree.root.show();
		//与原存储索引树进行比对
	}
	/**
	 * 非同步文件同步
	 * @param file
	 */
	abstract public void doFile(File file);
}
