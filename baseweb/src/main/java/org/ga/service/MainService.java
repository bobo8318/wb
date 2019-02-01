package org.ga.service;

import org.ga.po.Admin;
import org.springframework.stereotype.Service;

@Service("service")
public interface MainService {
	public Admin login(String loginName, String loginPwd);
}
