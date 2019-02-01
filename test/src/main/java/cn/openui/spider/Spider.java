package cn.openui.spider;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2017/7/23.
 */
public class Spider implements Runnable {

    private int CRAWL_MODE;
    private String file;
    private int status;
    private List<String> initUrlList;



    private List<String> link;

    private void setSpider(){

    }
    public void run() {

    }

    private  List getFiles(String doclink){
        List result = new ArrayList();
        Parser parser = new Parser();
        String linkText = "";
        try {
            parser.setURL(doclink);
            parser.setEncoding("GBK");
            NodeFilter filter = new NodeClassFilter(LinkTag.class);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < list.size(); i++) {
                LinkTag node = (LinkTag) list.elementAt(i);
                String link = node.extractLink();
                if(link.contains(".pdf")){
                    linkText = node.getLinkText();
                    result.add(node.extractLink());
                    break;
                }
            }
        } catch (ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private void down(String url){

    }
}
