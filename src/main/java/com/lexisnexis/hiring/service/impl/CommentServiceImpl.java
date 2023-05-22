package com.lexisnexis.hiring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.UserNotFoundException;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentsRepository commentsRepository;

	public Comments createComment(Comments comment) {
		return commentsRepository.save(comment);
	}

	public List<Comments> getComments() {
		return commentsRepository.findAll();

	}
	public Comments getCommentsByCommentId(int comment_id) {
		return commentsRepository.findByCommentId(comment_id);

	}

	public Comments getCommentsByEmployeeId(int employee_id) {
		return commentsRepository.findByEmpId(employee_id);
	}

	public Comments getCommentsByCandidateId(int candidate_id) {
		return commentsRepository.findByCandidId(candidate_id);
	}

	public Comments updateComment(Comments comment, int comment_id) {
		Optional<Comments> commentId = commentsRepository.findById(comment_id);
		boolean present = commentId.isPresent();
		Comments comm = null;
		if (present == true) {
			comm = commentId.get();
			LocalDateTime now = LocalDateTime.now();
			comm.setComments(comment.getComments());
			comm.setUpdatedDate(now);
			comm.setResult(comment.getResult());
		}
		return commentsRepository.save(comm);
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