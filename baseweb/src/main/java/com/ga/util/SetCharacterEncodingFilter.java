
package com.ga.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        if("".equals(Config.encode)) {
            Config.encode="utf-8";
        }
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	HttpServletResponse httpResponse = (HttpServletResponse)response;
        try
        {
        	String uri = httpRequest.getRequestURI();
        	if(uri.indexOf(".jsp")>=0){
        		System.out.println("somebody is reading jsp file! forbidden!");
        		//httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        	}


             request.setCharacterEncoding("UTF-8");
             response.setContentType("text/html;charset=UTF-8");
                
             request.setAttribute("SystemName", Parameter.SystemName);

            chain.doFilter(httpRequest,httpResponse);
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