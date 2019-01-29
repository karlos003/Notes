package org.ltq.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger commentid;
	private String account;
	private String uname;
	private Date time;
	private String content;
	private int image;
	public Comment() {
		super();
	}
	public Comment(String account, Date time) {
		super();
		this.account = account;
		this.time = time;
	}
	public Comment(String account, String uname, Date time, String content) {
		super();
		this.account = account;
		this.uname = uname;
		this.time = time;
		this.content = content;
	}
	public Comment(String account, String uname, Date time, String content, int image) {
		super();
		this.account = account;
		this.uname = uname;
		this.time = time;
		this.content = content;
		this.image = image;
	}
	public Comment(BigInteger commentid, String account, String uname, Date time, String content, int image) {
		super();
		this.commentid = commentid;
		this.account = account;
		this.uname = uname;
		this.time = time;
		this.content = content;
		this.image = image;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public BigInteger getCommentid() {
		return commentid;
	}
	public void setCommentid(BigInteger commentid) {
		this.commentid = commentid;
	}
	
	

	
	
}
