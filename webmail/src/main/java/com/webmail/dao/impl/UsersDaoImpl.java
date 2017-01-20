package com.webmail.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.webmail.dao.UsersDao;
import com.webmail.domain.Users;

@Transactional
@Component(value="usersDao")
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users get(Serializable key) {
		return (Users) this.sessionFactory.getCurrentSession().get(Users.class, key);
	}

	@Override
	public void save(Users u) {
		this.sessionFactory.getCurrentSession().save(u);
	}

	@Override
	public void update(Users u) {
		this.sessionFactory.getCurrentSession().update(u);
	}

	@Override
	public void delete(Users u) {
		this.sessionFactory.getCurrentSession().delete(u);
	}

	@Override
	public Users get(String username, String password) {
		return (Users) this.sessionFactory.getCurrentSession()
				.createQuery("from Users u where u.username=:username and u.pwdHash = :password")
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
	}
}
