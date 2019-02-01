package main.java.org.ga.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import main.java.org.ga.po.Admin;
import main.java.org.hao.tools.SQLHelper;
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
		String sql = "select * from admin where loginname=? and loginpwd=?";
		
		cn = this.getConnection();
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, loginName);
			pst.setString(2, loginPwd);
			//pst.setInt(2, userType);
			rs = pst.executeQuery();
			SQLHelper helper = new SQLHelper();
			String[][] columns = {};
			List<Admin> result = helper.autoInsertData(rs, columns, Admin.class);
			if(result!=null&&!result.isEmpty())
				return result.get(0);
			 
			/*if (rs.next()) {
					Admin admin = new Admin();
					admin.setAdminid(rs.getInt("adminid"));
					admin.setAdminName(rs.getString("adminName"));
					admin.setUnitcode(rs.getString("unitcode"));
					admin.setUnitname(rs.getString("unitname"));
					admin.setUserType(rs.getInt("userType"));
					return admin;
			} else {
				return null;
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			this.closeConnection();
		}
		
		return null;
	}
}
