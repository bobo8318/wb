package pub.imba.util;

/**
 * 
 * @author sin 2013-4-18
 * ip地址格式转换
 *
 */
public class IPFormat {

	public static long iptolong(String strip){//
		if(strip==null||"".equals(strip)|| strip.indexOf(".")<0) return 0;
		int j=0; 
		int i=0; 
		long[]ip = new long[4]; 
		int position1 = strip.indexOf("."); 
		int position2 = strip.indexOf(".",position1+1); 
		int position3 = strip.indexOf(".",position2+1); 
		ip[0] = Long.parseLong(strip.substring(0,position1)); 
		ip[1] = Long.parseLong(strip.substring(position1+1,position2)); 
		ip[2] = Long.parseLong(strip.substring(position2+1,position3)); 
		ip[3] = Long.parseLong(strip.substring(position3+1)); 
		return(ip[0]<<24)+(ip[1]<<16)+(ip[2]<<8)+ip[3];//
	}
	public static String longtoip(long longip) {//

		StringBuffer sb = new StringBuffer(""); 
		sb.append(String.valueOf(longip>>>24));//
		sb.append("."); 
		sb.append(String.valueOf((longip&0x00ffffff)>>>16));//
		sb.append("."); 
		sb.append(String.valueOf((longip&0x0000ffff)>>>8)); 
		sb.append("."); 
		sb.append(String.valueOf(longip&0x000000ff)); 
		sb.append("."); 
		return sb.toString(); 
	} 
	
	public static float IpUseRate(String iparea, String ipResverse, int useCount){
		int ipcount = 0;
		if(iparea!=null&&iparea.indexOf('-')>0){
			String start = iparea.substring(0, iparea.indexOf('-'));
			String end = iparea.substring(iparea.indexOf('-')+1, iparea.length());
			
			ipcount = (int) (iptolong(end)-iptolong(start));
		}
		 if(ipcount!=0){
			 float rate = (float)(useCount/(float)ipcount);
			 return rate;
		 }
		 else
			 return 0;
	}
	public static  boolean inArea(String ipLimit, String ip){
		boolean flag = false;
		if(!"".equals(ipLimit)){
			String[] iplist = ipLimit.split(",");
			for(int i=0;i<iplist.length;i++){
				
				if(iplist[i].contains("-")){// 127.0.0.1 - 127.0.0.1
					String[] ipArea = iplist[i].split("-");
					if(ipArea.length>1){
						if(inIparea(ipArea[0], ipArea[1], ip)){
							flag = true;
							break;
						}
					}
				}else{//单个ip
					if(ip.equals(iplist[i])){
						flag = true;
						break;
					}
				}
			}
		}
		
		return flag;
	}
	
	public static boolean inIparea(String startIp, String endIp, String testIp){
		long start = iptolong(startIp);
		long end = iptolong(endIp);
		long test = iptolong(testIp);
		if(test>=start&&test<=end&&test!=0){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String iparea =  "-";
		/*if(iparea!=null&&iparea.indexOf('-')>0){
			String start = iparea.substring(0, iparea.indexOf('-'));
			String end = iparea.substring(iparea.indexOf('-')+1, iparea.length());
			System.out.println("start:"+start+" end:"+end);
		}*/
		//IpUseRate(iparea,"",0);
		String ipLimit = "192.168.0.1-192.168.1.1,192.168.1.4";
		if(IPFormat.inArea(ipLimit, "192.168.1.3")){
			System.out.println("in this area");
		}else{
			System.out.println("not in this area");
		}
	}

}
