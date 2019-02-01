package main.java.org.ga.service.imply;

import javax.annotation.Resource;

import main.java.org.ga.dao.LoginDao;
import main.java.org.ga.po.Admin;
import main.java.org.ga.service.MainService;
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
