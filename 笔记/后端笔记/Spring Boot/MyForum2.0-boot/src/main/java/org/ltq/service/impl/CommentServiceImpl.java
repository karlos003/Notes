package org.ltq.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.ltq.entity.Comment;
import org.ltq.mapper.CommentMapper;
import org.ltq.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
	}

	public List<Comment> queryAllComments() {
		List<Comment> comments = commentMapper.queryAllComments();
		return comments;
	}

	public boolean deleteCommentByCommentId(BigInteger commentId) {
		int count = commentMapper.queryCommentCountByCommentId(commentId);
		if(count!=1) {
			return false;
		}else if(count==1) {
			commentMapper.deleteCommentByCommentId(commentId);
			return true;
		}
		return false;
	}

	public BigInteger queryCommentIdByAccountAndTime(Comment comment) {
		BigInteger commentId = commentMapper.queryCommentIdByAccountAndTime(comment);
		return commentId;
	}

}
