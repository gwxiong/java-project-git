package com.webmail.dao;

import java.io.Serializable;
import com.webmail.domain.Users;

public interface UsersDao {
	public Users get(Serializable key);
	public void save(Users u);
	public void update(Users u);
	public void delete(Users u);
	
	public Users get(String username,String password);
}
