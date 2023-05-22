package com.lexisnexis.hiring.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.UserNotFoundException;
import com.lexisnexis.hiring.response.APIResponse;

public interface CommentService {
	
	public APIResponse createComment(Comments comment) throws NoSuchAlgorithmException;
	
	public List<Comments> getComments();
	
	public Comments getCommentsByCommentId(int comment_id);
	
	public Comments getCommentsByEmployeeId(int employee_id);
	
	public Comments getCommentsByCandidateId(int candidate_id);
	
	public APIResponse updateComment(Comments comment, int comment_id);
	
	public void deleteComment(int comment_id) throws UserNotFoundException;
}
