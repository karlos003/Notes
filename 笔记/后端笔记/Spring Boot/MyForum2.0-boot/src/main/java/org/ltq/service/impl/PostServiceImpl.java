package org.ltq.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.ltq.entity.Post;
import org.ltq.mapper.PostMapper;
import org.ltq.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	
	public void setPostMapper(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	public void addPost(Post post) {
		postMapper.addPost(post);
	}

	public BigInteger selectPostIdByAccountAndTime(Post post) {
		BigInteger post_id = postMapper.selectPostIdByAccountAndTime(post);
		return post_id;
	}

	public List<Post> queryPostInfoByTypeOrdByTime(String type) {
		List<Post> posts = postMapper.queryPostInfoByTypeOrdByTime(type);
		return posts;
	}

}
