package org.ltq.service;

import java.math.BigInteger;
import java.util.List;
import org.ltq.entity.Post;

public interface PostService {
	void addPost(Post post);
	BigInteger selectPostIdByAccountAndTime(Post post);
	List<Post> queryPostInfoByTypeOrdByTime(String type);
}
