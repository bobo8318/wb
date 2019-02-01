package org.ga.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.ga.po.Admin;
import org.hao.tools.SQLHelper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("loginDao")
public class LoginDao extends BaseDao{

	/**
	 * 查询用户密码是否正确
	 * 
	 * @param loginName
	 * @param loginPwd
	 * @return

	 */
	public Admin checkPassword(String loginName, String loginPwd) {
		String sql = "select loginName from openadmin where loginname=? and password=?";
		List<Admin> list = this.jdbcTemplate.query(sql, new Object[] { loginName,loginPwd }, new BeanPropertyRowMapper(Admin.class));

		System.out.println(loginName+" "+loginPwd+" "+list.size());
		if(!list.isEmpty())
		return list.get(0);
		else return null;
	}
}
