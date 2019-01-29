package org.ltq.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String account;
	private String pwd;
	private String name;
	private int user_photo;
	public User() {
		super();
	}
	public User(String account, int user_photo) {
		super();
		this.account = account;
		this.user_photo = user_photo;
	}
	public User(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
	}
	public User(String account, String pwd, String name) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
	}
	public User(String account, String pwd, String name, int user_photo) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.user_photo = user_photo;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(int user_photo) {
		this.user_photo = user_photo;
	}
	
	
}
