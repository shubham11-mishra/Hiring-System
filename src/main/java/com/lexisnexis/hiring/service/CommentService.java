package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;

import java.util.List;

public interface CommentService {
	
	public Comments createComment(Comments comment) ;
	
	public List<Comments> getComments();
	
	public Comments getCommentsByCommentId(int comment_id) throws CommentIdNotFoundException;
	
	public List<Comments> getCommentsByEmployeeId(int employee_id);
	
	public List<Comments> getCommentsByCandidateId(int candidate_id);
	
	public Comments updateComment(Comments comment, int commentId) throws CommentIdNotFoundException;
	
	public void deleteComment(int comment_id) throws CommentIdNotFoundException;
}
