package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.UserNotFoundException;

import java.util.List;

public interface CommentService {
	
	public Comments createComment(Comments comment) ;
	
	public List<Comments> getComments();
	
	public Comments getCommentsByCommentId(int comment_id);
	
	public Comments getCommentsByEmployeeId(int employee_id);
	
	public Comments getCommentsByCandidateId(int candidate_id);
	
	public Comments updateComment(Comments comment, int comment_id);
	
	public void deleteComment(int comment_id) throws UserNotFoundException;
}
