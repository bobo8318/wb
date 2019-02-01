package main.java.org.ga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wabacus.config.Config;

public class DataSourceManager {
	/**
	 * 获取链接
	 * 第一个是通过wabacus框架 提供连接 由于rop框架加载在wabacus之前
	 * 所以只能使用普通的方式活动数据库连接。
	 * @return
	 */
	public static Connection  getConnection(){
		
		//System.out.println("---get database connection---:"+Config.getInstance().getDataSource("ds_mysql").getConnection());
		Connection conn = null;
		try{
			
			conn = Config.getInstance().getDataSource("ds_sqlserver2k").getConnection();
			
			
		}catch(Exception a){
			
		}
		return conn;
	}
	
	/**
	 * 关闭链接对象
	 * @param rs
	 * @param st
	 * @param cn
	 */
	public static void closeConnection(ResultSet rs, PreparedStatement pst, Statement st,Connection cn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(st!=null){
				st.close();
			}
			if(cn!=null&&!cn.isClosed()){
				cn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
