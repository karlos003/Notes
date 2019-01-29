package org.ltq.mapper;

import java.math.BigInteger;
import java.util.List;
import org.ltq.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
	void deleteCommentByCommentId(BigInteger commentId);
	int queryCommentCountByCommentId(BigInteger commentId);
	List<Comment> queryAllComments();
	BigInteger queryCommentIdByAccountAndTime(Comment comment);
	void addComment(Comment comment);
}
