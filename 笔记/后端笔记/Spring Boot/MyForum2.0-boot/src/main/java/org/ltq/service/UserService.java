package org.ltq.service;

import org.ltq.entity.User;

public interface UserService {
	boolean addUser(User user);
	void updateUserPhotoByAccount(User user);
	void updateUserPwdByAccount(User user);
	User queryUserByAccount(String account);
	String queryUserPwdByAccount(String account);
}
