
package com.ga.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wabacus.config.Config;

public class SetCharacterEncodingFilter implements Filter
{
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException
    {

        this.filterConfig = filterConfig;
    }
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
    {
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	HttpServletResponse httpResponse = (HttpServletResponse)response;
        try
        {
        	String uri = httpRequest.getRequestURI();
        	if(uri.indexOf(".jsp")>=0){
        		System.out.println("somebody is reading jsp file! forbidden!");
        		httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        	}
            if(!Config.encode.equalsIgnoreCase("utf-8"))
            {//如果当前项目采用的不是UTF-8编码
               
                String contentType=httpRequest.getContentType();
                if (contentType != null
                        && contentType.toLowerCase().startsWith(
                                "application/x-www-form-urlencoded; charset=utf-8"))
                {//报表提交
                    request.setCharacterEncoding("UTF-8");
                }else
                {
                    request.setCharacterEncoding(Config.encode);
                }
                response.setContentType("text/html; charset="+Config.encode);
            }else
            {
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                
                request.setAttribute("SystemName", Parameter.SystemName);
            }
            chain.doFilter(request,response);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void destroy()
    {
        this.filterConfig = null;
    }
}