package org.ltq.mapper;

import java.math.BigInteger;
import java.util.List;

import org.ltq.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	void addPost(Post post);
	BigInteger selectPostIdByAccountAndTime(Post post);
	List<Post> queryPostInfoByTypeOrdByTime(String type);
}
