package cn.openui.tools;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by My on 2018/3/22.
 */
public class CommonTools {

    public static void showList(List list){
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    public static String ListToString(List list){
        StringBuffer sb = new StringBuffer();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            sb.append(iterator.next().toString());
            sb.append(",");
        }
        return sb.toString();
    }

    /**
     * sin 2013-9-4
     * 取时间  用于文件重命名
     */
    public static String FileRenameByTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = sdf.format(new Date());
        return filename;
    }
    /**
     * sin 2016-10-27
     *
     */
    public static String getRate(String son, String mother, String fmt){
        if(fmt==null||"".equals(fmt))
            fmt = "#.00";
        DecimalFormat df = new DecimalFormat(fmt);
        int sonInt=0,motherInt=0;
        if(son!=null&&!"".equals(son.trim())){
            sonInt = Integer.parseInt(son.trim());
        }
        if(mother!=null&&!"".equals(mother.trim())){
            motherInt = Integer.parseInt(mother.trim());
        }

        if(sonInt==0||motherInt==0) return "0";
        String result = df.format(((double)sonInt/motherInt)*100);
        return result;
    }

    public static String firstLetterUp(String str){
        if(str!=null&&!"".equals(str)){
            Character first = Character.toUpperCase(str.charAt(0));
            str = str.replaceFirst(""+str.charAt(0), ""+first);

        }
        return str;
    }
    public static  void main(String[] args){

    }

}
