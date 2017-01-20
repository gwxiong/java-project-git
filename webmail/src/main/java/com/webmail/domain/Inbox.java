package com.webmail.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

/**
 * 收件箱
 * @author Guowx
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="inboxs")
public class Inbox implements Serializable {
	
	@Id
	@GeneratedValue(generator="hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@Column(name="id")
	private String id;			//编号

	@Column(name="sender")
	private String sender;		//发送人

	@Column(name="recipient")
	private String recipient;	//接收人

	@Column(name="body")
	private Blob body;			//邮件内容

	@Column(name="lastTime")
	private Date lastTime;		//时间

	@Column(name="isRead")
	@ColumnDefault("0")
	private Boolean isRead;		//是否已读
	
	@Column(name="isDrop")
	@ColumnDefault("0")
	private Boolean isDrop;		//是否删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Blob getBody() {
		return body;
	}

	public void setBody(Blob body) {
		this.body = body;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsDrop() {
		return isDrop;
	}

	public void setIsDrop(Boolean isDrop) {
		this.isDrop = isDrop;
	}
}
