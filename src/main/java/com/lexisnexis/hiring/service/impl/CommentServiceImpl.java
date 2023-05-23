package com.lexisnexis.hiring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.exception.CandidateIdIncorrect;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Comments createComment(Comments comment) {
		if (employeeRepository.findById(comment.getEmployee().getEmployeeId()).isEmpty()) {
			throw new NoEmployeeFoundException("Employee id Incorrect");
		} else if (candidateRepository.findById(comment.getCandidate().getCandidateId()).isEmpty()) {
			throw new CandidateIdIncorrect("candidate id Incorrect");
		} else {
			return commentsRepository.save(comment);
		}

	}

	@Override
	public List<Comments> getComments() throws NullPointerException {
		if (commentsRepository.findAll() != null) {
			return commentsRepository.findAll();
		} else {
			throw new NullPointerException("No Comments Found!!");
		}

	}

	@Override
	public Comments getCommentsByCommentId(int comment_id) throws CommentIdNotFoundException {
		if (commentsRepository.findById(comment_id) == null) {
			throw new CommentIdNotFoundException("Comment id incorrect");
		} else {
			return commentsRepository.findByCommentId(comment_id);
		}

	}

	@Override
	public List<Comments> getCommentsByEmployeeId(int employee_id) {
		if (commentsRepository.findByEmpId(employee_id) == null) {
			throw new NoEmployeeFoundException("Employee id incorrect");
		} else {
			return commentsRepository.findByEmpId(employee_id);
		}

	}

	@Override
	public List<Comments> getCommentsByCandidateId(int candidate_id) {
		if (commentsRepository.findByCandidId(candidate_id) == null) {
			throw new CandidateIdIncorrect("Candidate id incorrect");
		}
		return commentsRepository.findByCandidId(candidate_id);
	}

	@Override
	public Comments updateComment(Comments comment, int commentId) throws CommentIdNotFoundException {
		System.out.print(comment.getComments());
		Comments existingComment = commentsRepository.findByCommentId(commentId);

		if (existingComment != null) {

			if (comment.getResult() != null) {
				existingComment.setResult(comment.getResult());
			}
			if (comment.getComments() != null) {
				existingComment.setComments(comment.getComments());
			}

			LocalDateTime now = LocalDateTime.now();
			existingComment.setUpdatedDate(now);

			return commentsRepository.save(existingComment);

		} else {
			throw new CommentIdNotFoundException("invalid comment id");
		}

	}

	@Override
	public void deleteComment(int comment_id) throws CommentIdNotFoundException {
		Comments comment = commentsRepository.findByCommentId(comment_id);
		if (comment != null) {
			commentsRepository.deleteById(comment_id);
		} else {
			throw new CommentIdNotFoundException("comment id not found" + " " + comment_id);
		}
	}

}