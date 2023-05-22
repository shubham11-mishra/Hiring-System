package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.advice.APIResponse;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdFoundException;

import java.util.List;

public interface CommentService {
	
	public Comments createComment(Comments comment) ;
	
	public List<Comments> getComments();
	
	public Comments getCommentsByCommentId(int comment_id) throws CommentIdFoundException;
	
	public Comments getCommentsByEmployeeId(int employee_id);
	
	public Comments getCommentsByCandidateId(int candidate_id);
	
	public APIResponse updateComment(Comments comment, int commentId);
	
	public void deleteComment(int comment_id) throws CommentIdFoundException;
}
