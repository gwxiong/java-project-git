package com.webmail.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户信息
 * @author Guowx
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="userinfo")
public class UserInfo implements Serializable {

	@Id
	@GeneratedValue(generator="hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@Column(name="id")
	private String id;			//编号

	@Column(name="username")
	private String username;	//用户名

	@Column(name="password")
	private String password;	//密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
