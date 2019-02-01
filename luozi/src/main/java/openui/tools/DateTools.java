package openui.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
    public static String getFormatDate(Date date, String format){
        String default_format = "yyyy-MM-dd";
        if(!TextUtil.isEmpty(format)){
            default_format = format;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(default_format);
        return sdf.format(date);
    }
}
