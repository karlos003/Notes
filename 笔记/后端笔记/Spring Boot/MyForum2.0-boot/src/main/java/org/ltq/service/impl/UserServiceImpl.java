package org.ltq.service.impl;

import org.ltq.entity.User;
import org.ltq.mapper.UserMapper;
import org.ltq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public boolean addUser(User user) {
		int userCount = userMapper.queryUserCountByAccount(user.getAccount());
		if(userCount==0) {
			userMapper.addUser(user);
			return true;
		}else {
			return false;
		}
	}

	public User queryUserByAccount(String account) {
		User user = userMapper.queryUserByAccount(account);
		return user;
	}

	public void updateUserPhotoByAccount(User user) {
		int count = userMapper.queryUserCountByAccount(user.getAccount());
		if(count==1) {
			userMapper.updateUserPhotoByAccount(user);
		}
	}

	public void updateUserPwdByAccount(User user) {
		userMapper.updateUserPwdByAccount(user);
	}

	@Override
	public String queryUserPwdByAccount(String account) {
		String pwd = userMapper.queryUserPwdByAccount(account);
		return pwd;
	}

}
