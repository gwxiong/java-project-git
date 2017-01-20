package com.webmail.service;

import java.io.Serializable;
import com.webmail.domain.Users;

public interface UsersService {
	public Users query(Serializable key);
	public void add(Users u);
	public void modify(Users u);
	public void remove(Users u);
	
	public Users checkNameAndPwd(String username,String password);	//检查用户名和密码
}
