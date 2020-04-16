package pub.imba.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sin on 2018/9/6.
 */
public class FolderUtil {

    public static String folderDelete(File file, int current, int deep) {
        String result = "";
        if(file.exists() && current<=deep){
            if(file.isDirectory()){//
                File[] childlist = file.listFiles();
                if(childlist!=null&&childlist.length>0){
                    boolean flag = false;
                    //System.out.println(" find no empty folder:"+file);
                    for(File childfile:childlist){
                        result += folderDelete(childfile,current+1,deep);
                    }
                    result += " remove clear folder:"+file.getAbsolutePath()+" "+ file.delete();
                   // System.out.println(result);
                    return result+" \\n";
                }else{
                    result += " remove empty folder:"+file.getAbsolutePath()+" "+ file.delete();
                   // System.out.println(result);
                    return result+" \\n";
                }
            }else if(file.isFile()){
                result += " remove file:"+file.getAbsolutePath()+" "+ file.delete();
                //System.out.println(result);
               return  result+" \\n";
            }
        }
        result += " file not found or deep jump:"+file.getAbsolutePath();
        //System.out.println(result);
        return result+" \\n";
    }

    /**
     *
     */
    public static List<Object[]> getFiles(File root, int currentdepth, int depth) {
        List<Object[]> result = new ArrayList<>();
        if(root.exists()&&root.isDirectory()) {
            if (currentdepth <= depth){
                File[] files = root.listFiles();
                for (File file : files) {
                    result.add(new Object[]{file, currentdepth});
                    result.addAll(getFiles(file, currentdepth + 1, depth));
                }
            }else
                return new ArrayList<>();
        }
        return result;
    }

    public static String getRelativePath(String absolutePath, String root) {
        if(!TextUtil.isEmpty(absolutePath)&&!TextUtil.isEmpty(root)){
            String path = absolutePath.replace(root,"");

            return path.replace("\\","/");
        }else
            return "";

    }
}
