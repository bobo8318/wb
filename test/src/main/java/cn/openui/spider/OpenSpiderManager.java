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
public class OpenSpiderManager {

    private final int  CRAWL_MODE_SITE = 0;//仅在站内搜索
    private final int  CRAWL_MODE_ALL = 1;//全网搜索

    private int thread;
    private List<Spider> spiderPool;//爬虫池
    private List<Ant> antPool;//蚂蚁池 下载


}
