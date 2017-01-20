package com.webmail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webmail.dao.UserInfoDao;
import com.webmail.domain.UserInfo;
import com.webmail.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public void add(UserInfo userInfo) {
		userInfoDao.save(userInfo);
	}

}
