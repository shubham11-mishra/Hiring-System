package com.lexisnexis.hiring.service.impl;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.UserNotFoundException;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.response.APIResponse;
import com.lexisnexis.hiring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentsRepository commentsRepository;

	public APIResponse createComment(Comments comment) throws NoSuchAlgorithmException {
		APIResponse apiResponse = new APIResponse();
		commentsRepository.save(comment);
		apiResponse.setData("Comment saved succesfully");

		return apiResponse;
	}

	public List<Comments> getComments() {
		return commentsRepository.findAll();

	}

//public Optional<Comments> getCommentsById(int comment_id) {
//	return commentsRepository.findById(comment_id);
//
//}

	public Comments getCommentsByCommentId(int comment_id) {
		return commentsRepository.findByCommentId(comment_id);

	}

	public Comments getCommentsByEmployeeId(int employee_id) {

		return commentsRepository.findByEmpId(employee_id);

	}

	public Comments getCommentsByCandidateId(int candidate_id) {

		return commentsRepository.findByCandidId(candidate_id);

	}

	public APIResponse updateComment(Comments comment, int comment_id) {
		APIResponse apiResponse = new APIResponse();
		Optional<Comments> commentId = commentsRepository.findById(comment_id);

		boolean present = commentId.isPresent();
		if (present == true) {

			LocalDateTime now = LocalDateTime.now();
			Comments comm = commentId.get();
			comm.setComments(comment.getComments());
			comm.setUpdatedDate(now);
			comm.setResult(comment.getResult());
			commentsRepository.save(comm);
			apiResponse.setData("updated Successfully");
		}
		return apiResponse;
	}

	public void deleteComment(int comment_id) throws UserNotFoundException {
		Comments comment = commentsRepository.findByCommentId(comment_id);
		if (comment != null) {
			commentsRepository.deleteById(comment_id);
		} else {
			throw new UserNotFoundException("comment id not found" + " " + comment_id);
		}

	}

}