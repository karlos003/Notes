package org.ltq.service;

import java.math.BigInteger;
import java.util.List;
import org.ltq.entity.Comment;

public interface CommentService {
	void addComment(Comment comment);
	boolean deleteCommentByCommentId(BigInteger commentId);
	List<Comment> queryAllComments();
	BigInteger queryCommentIdByAccountAndTime(Comment comment);
}
