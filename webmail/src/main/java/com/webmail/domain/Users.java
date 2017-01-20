package com.webmail.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

/**
 * James用户（字段不可乱动）
 * @author Guowx
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class Users implements Serializable  {

	@Id
	@Column(name="username")
	private String username;

	@Column(name="pwdHash")
	private String pwdHash;

	@ColumnDefault("SHA")
	@Column(name="pwdAlgorithm")
	private String pwdAlgorithm;

	@ColumnDefault("0")
	@Column(name="useForwarding")
	private int useForwarding;

	@Column(name="forwardDestination")
	private String forwardDestination;

	@ColumnDefault("0")
	@Column(name="useAlias")
	private int useAlias;

	@Column(name="alias")
	private String alias;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public String getPwdAlgorithm() {
		return pwdAlgorithm;
	}

	public void setPwdAlgorithm(String pwdAlgorithm) {
		this.pwdAlgorithm = pwdAlgorithm;
	}

	public int getUseForwarding() {
		return useForwarding;
	}

	public void setUseForwarding(int useForwarding) {
		this.useForwarding = useForwarding;
	}

	public String getForwardDestination() {
		return forwardDestination;
	}

	public void setForwardDestination(String forwardDestination) {
		this.forwardDestination = forwardDestination;
	}

	public int getUseAlias() {
		return useAlias;
	}

	public void setUseAlias(int useAlias) {
		this.useAlias = useAlias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
