package com.webmail.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.webmail.dao.UserInfoDao;
import com.webmail.domain.UserInfo;

@Transactional
@Component(value="userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserInfo userInfo) {
		this.sessionFactory.getCurrentSession().save(userInfo);
	}
}
