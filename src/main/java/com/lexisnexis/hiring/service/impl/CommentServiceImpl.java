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
import com.lexisnexis.hiring.exception.NoCommentsFoundException;
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
	public List<Comments> getComments() throws NoCommentsFoundException {
		if (commentsRepository.findAll() != null) {
			return commentsRepository.findAll();
		} else {
			throw new NoCommentsFoundException("No Comments Found!!");
		}

	}

	@Override
	public Comments getCommentsByCommentId(int commentId) throws CommentIdNotFoundException {
		if (commentsRepository.findById(commentId) == null) {
			throw new CommentIdNotFoundException("Comment id incorrect");
		} else {
			return commentsRepository.findById(commentId).get();
		}

	}

	@Override
	public List<Comments> getCommentsByEmployeeId(int employeeId) throws NoCommentsFoundException {
		if (commentsRepository.findByEmpId(employeeId) == null) {
			throw new NoCommentsFoundException("NO Comments found with that Employee ID");
		} else {
			return commentsRepository.findByEmpId(employeeId);
		}
	}

	@Override
	public List<Comments> getCommentsByCandidateId(int candidateId) throws NoCommentsFoundException {
		if (commentsRepository.findByCandidId(candidateId) == null) {
			throw new NoCommentsFoundException("NO Comments found with that Candidate id");
		}
		return commentsRepository.findByCandidId(candidateId);
	}

	@Override
	public Comments updateComment(Comments comment, int commentId) throws CommentIdNotFoundException {
		Comments existingComment = commentsRepository.findByCommentId(commentId);
		if (existingComment != null) {
			if (comment.getResult() != null) {
				existingComment.setResult(comment.getResult());
			}
			if (comment.getComments() != null) {
				existingComment.setComments(comment.getComments());
			}
			return commentsRepository.save(existingComment);
		} else {
			throw new CommentIdNotFoundException("Invalid comment id");
		}
	}

	@Override
	public void deleteComment(int commentId) throws CommentIdNotFoundException {
		Comments comment = commentsRepository.findByCommentId(commentId);
		if (comment != null) {
			commentsRepository.deleteById(commentId);
		} else {
			throw new CommentIdNotFoundException("comment id not found" + " " + commentId);
		}
	}

}