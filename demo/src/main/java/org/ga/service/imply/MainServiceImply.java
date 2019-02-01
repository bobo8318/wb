package org.ga.service.imply;

import javax.annotation.Resource;

import org.ga.dao.LoginDao;
import org.ga.po.Admin;
import org.ga.service.MainService;
import org.springframework.stereotype.Service;
@Service
public class MainServiceImply implements MainService {

	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Override
	public Admin login(String loginName, String loginPwd) {
		// TODO Auto-generated method stub
		return loginDao.checkPassword(loginName, loginPwd);
	}

	

}
