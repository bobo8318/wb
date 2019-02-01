package org.ga.dao;
/**
 * 
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository
public class BaseDao {

	protected DataSource springDataSource;
	protected JdbcTemplate jdbcTemplate;

	Connection cn = null;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs  = null;
	CallableStatement callableStatement = null;

	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.springDataSource = dataSource;
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public DataSource getDataSource() {
	    return springDataSource;
	}

	public JdbcTemplate getSimpleJdbcTemplate(){
		//this.jdbcTemplate = new SimpleJdbcTemplate(DataSourceManager.);
		return this.jdbcTemplate;
	}
	
	
	public Connection getConnection(){
		return DataSourceManager.getConnection();
	}
	public void closeConnection(){
		DataSourceManager.closeConnection(rs, pst, st, cn);
	}
	public void closeCallConnection(){
		DataSourceManager.closeConnection(rs, null, callableStatement, cn);
	}
}