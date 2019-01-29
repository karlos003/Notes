package org.ltq.mapper;

import org.ltq.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	int queryUserCountByAccount(String id);
	User queryUserByAccount(String account);
	void addUser(User user);
	void updateUserPhotoByAccount(User user);
	void updateUserPwdByAccount(User user);
	String queryUserPwdByAccount(String account);
}
