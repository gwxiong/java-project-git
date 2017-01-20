package com.webmail.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webmail.dao.UsersDao;
import com.webmail.domain.Users;
import com.webmail.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDao usersDao;

	@Override
	public Users query(Serializable key) {
		return usersDao.get(key);
	}

	@Override
	public void add(Users u) {
		usersDao.save(u);
	}

	@Override
	public void modify(Users u) {
		usersDao.update(u);
	}

	@Override
	public void remove(Users u) {
		usersDao.delete(u);
	}

	@Override
	public Users checkNameAndPwd(String username,String password) {
		return usersDao.get(username,password);
	}

}
