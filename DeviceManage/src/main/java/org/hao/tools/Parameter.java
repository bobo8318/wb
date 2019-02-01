package main.java.org.hao.tools;

public class Parameter {

	
	public static String VERSION = "V1.3.0";
	
	public static enum DATA_SOURCE{MYSQL,MSSQL};
	public static enum SKIN{DEFAULT,BOOTSTRAP,MAC};
	public static int FLOOD_ACCOUNT = 0;
	public static int FS_ACCOUNT = 1;
	
	public static int MYSQL = 0;
	public static int MSSQL = 1;
	
	public static int READ_ONLY = 0;
	public static int UPDATE_ONLY = 1;
	public static int ADD_REMOVE = 2;
	
	public static enum USER_TYPE{SYSTEM_USER,PERSONAL_USER};
	public static int SYSTEM_USER = 1;
	public static int PERSONAL_USER = 2;
	
	public static enum ADMIN_LEVEL{SUPER_ADMIN(0),LESSER_ADMIN(2),COMMON_ADMIN(1),COMMON_POLICE(3);
		private final int admin_level;
		private ADMIN_LEVEL(int level){
			this.admin_level = level;
		}
		public int getIntAdminLevel(){
			return admin_level;
		}
		public static ADMIN_LEVEL getEnumAdminLevel(int level){
			switch(level){
			case 0:return SUPER_ADMIN;
			case 1:return LESSER_ADMIN;
			case 2:return COMMON_ADMIN;
			case 3:return COMMON_POLICE;
			default:return COMMON_POLICE;
			}
			
		}
	};
	public static int SUPER_ADMIN = 0;
	public static int LESSER_ADMIN = 1;
	public static int COMMON_ADMIN = 2;
	public static int COMMON_POLICE = 3;
	
	public static enum DateFormate{yyyy_MM_dd,yyyy_MM,yyyy,dd,MM_dd,MM};
	/*public static int yyyy_MM_dd = 0;
	public static int yyyy_MM = 1;
	public static int yyyy = 2;
	public static int MM = 3;
	public static int dd = 4;
	public static int MM_dd = 5;*/
	
	//public static String IMG_SERVER_URL = "http://10.12.93.10/imgserver";
	//系统参数
	public static int PAGE_SIZE = 20;
	
	public static String IMG_SERVER_URL = "http://10.12.93.13:8087";
	//相册图片服务器地址
	public static String GALLERY_SERVER_URL = "http://10.12.93.13:8087/gallery";
	//视频服务器地址
	public static String VIDEO_SERVER_URL = "http://10.12.93.13:8087/video";
	
	public static String SWF_URL = "http://10.12.93.13:8980/haorop/";
	public static String SWF_SAVE_URL = "E:\\cms\\imgserver\\haorop\\swf";
	public static String DOC_SAVE_URL = "E:\\cms\\imgserver\\haorop\\doc";
	public static String PDF_SAVE_URL = "E:\\cms\\imgserver\\haorop\\doc";
	
	public static String IMG_SAVE_URL = "E:\\cms\\imgserver\\haorop\\wabacusdemo\\uploadfile";
	
	//public static String URL = "E:\\cms\\imgserver\\haorop\\wabacusdemo\\uploadfile";
	
	public static String OUT_TIME_PAGE = "outtime.jsp";
	public static String FILE_URL;
	
	public static String IE6MAIN = "main.jsp";
	public static String BSMAIN = "bsmain.jsp";
	
	public static String WORDSBAN;
	
	public static String HTML_TEMPLATE_HOST;
	public static String HTML_TEMPLATE_SAVEPATH;
	
	public static int RENT_LOG = 1;
	
	public static int INT_TYPE = 1;
	public static int Stirng_TYPE = 2;
	
}
