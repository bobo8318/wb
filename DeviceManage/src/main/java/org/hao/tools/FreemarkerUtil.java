package main.java.org.hao.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	 /**
     * 获取模板
     * @param name
     * @return
     */
    public Template getTemplate(String name, String coding) {
    try {
    	if(coding==null||"".equals(coding))
    		coding = "utf-8";
        //通过Freemaker的Configuration读取相应的ftl
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.CHINA, "utf-8");
      		//cfg.setDefaultEncoding("UTF-8");
        //设定去哪里读取相应的ftl模板文件
        cfg.setClassForTemplateLoading(this.getClass(),"ftl");
        //在模板文件目录中找到名称为name的文件
        Template temp = cfg.getTemplate(name);
        return temp;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
    }
    /**
     * 输出到控制台
     * @param name 模板文件名
     * @param root 
     */
    public void print(String name,Map<String,Object> root) {
        try {
            //通过Template可以将模板文件输出到相应的流
        Template temp = this.getTemplate(name,"");
        temp.process(root, new PrintWriter(System.out));
    } catch (TemplateException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    /**
     * 输出到文件
     * @param name
     * @param root
     * @param outFile
     */
    public void fprint(String name,Map<String,
                       Object> root,String outFile) {
    FileWriter out = null;
    try {
            //通过一个文件输出流，就可以写到相应的文件中
        out = new FileWriter(
                      new File(Parameter.HTML_TEMPLATE_SAVEPATH+outFile));
        Template temp = this.getTemplate(name,"gbk");
        temp.process(root, out);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    } finally {
        try {
        if(out!=null) out.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    }
}
