package com.lexisnexis.hiring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexisnexis.hiring.advice.APIResponse;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.exception.CandidateIdIncorrect;
import com.lexisnexis.hiring.exception.CommentIdFoundException;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentsRepository commentsRepository;

	@Override
	public Comments createComment(Comments comment) {
		if (commentsRepository.findById(comment.getEmployee().getEmployeeId()).isEmpty()) {
			throw new NoEmployeeFoundException("Employee id Incorrect");
		} else if (commentsRepository.findById(comment.getCandidate().getCandidateId()).isEmpty()) {
			throw new CandidateIdIncorrect("candidate id Incorrect");
		} else {
			return commentsRepository.save(comment);
		}

	}

	@Override
	public List<Comments> getComments() {
		return commentsRepository.findAll();

	}

	@Override
	public Comments getCommentsByCommentId(int comment_id) throws CommentIdFoundException {
		if (commentsRepository.findById(comment_id) == null) {
			throw new CommentIdFoundException("Comment id incorrect");
		} else {
			return commentsRepository.findByCommentId(comment_id);
		}

	}

	@Override
	public Comments getCommentsByEmployeeId(int employee_id) {
		if (commentsRepository.findByEmpId(employee_id) == null) {
			throw new NoEmployeeFoundException("Employee id incorrect");
		} else {
			return commentsRepository.findByEmpId(employee_id);
		}

	}

	@Override
	public Comments getCommentsByCandidateId(int candidate_id) {
		if (commentsRepository.findByCandidId(candidate_id) == null) {
			throw new CandidateIdIncorrect("Candidate id incorrect");
		}
		return commentsRepository.findByCandidId(candidate_id);
	}

	// update comment by employee id
//	@Override
//	public Comments updateComment(Comments comment) {
//
//		Comments c = new Comments();
//		Employee e = new Employee();
//		c.setEmployee(e);
//		c.getEmployee().setEmployeeId(comment.getEmployee().getEmployeeId());
//
//		if (commentsRepository.findById(comment.getEmployee().getEmployeeId()).isEmpty()) {
//			throw new NoEmployeeFoundException("employee or candidate id is inncorrect");
//		} else if (commentsRepository.findById(comment.getCandidate().getCandidateId()).isEmpty()) {
//			throw new CandidateIdIncorrect("candidate id Incorrect");
//		}
//
//		else {
//
//			Optional<Comments> commentId = commentsRepository.findById(comment.getEmployee().getEmployeeId());
//			boolean present = commentId.isPresent();
//			Comments comm = commentId.get();
//			if (present == true) {
//				comm = commentId.get();
//				LocalDateTime now = LocalDateTime.now();
//				comm.setComments(comment.getComments());
//				comm.setUpdatedDate(now);
//				comm.setResult(comment.getResult());
//			}
//			return commentsRepository.save(comm);
//		}
//
//	}

	
	@Override
	public APIResponse updateComment(Comments comment, int commentId) {
	    APIResponse apiResponse = new APIResponse();

	    if (commentsRepository.findById(commentId) == null) {
			throw new NoEmployeeFoundException("commentId is inncorrect");
		} 
	    else {
	    	Optional<Comments> optionalComment = commentsRepository.findById(commentId);
		    if (optionalComment.isPresent()) {
		        Comments existingComment = optionalComment.get();
		        
		        // Update the comment properties
		        existingComment.setComments(comment.getComments());
		        
		        LocalDateTime now = LocalDateTime.now();

		        existingComment.setUpdatedDate(now);
		        
		        existingComment.setResult(comment.getResult());
		        
		        // Save the updated comment
		        commentsRepository.save(existingComment);
		        
		        apiResponse.setData("Comment updated successfully");
		    } else {
		        apiResponse.setData("Comment with the specified commentId does not exist");
		    }
		    
		    return apiResponse;
	    }
	  
	}
	

	@Override
	public void deleteComment(int comment_id) throws CommentIdFoundException {
		Comments comment = commentsRepository.findByCommentId(comment_id);
		if (comment != null) {
			commentsRepository.deleteById(comment_id);
		} else {
			throw new CommentIdFoundException("comment id not found" + " " + comment_id);
		}
	}

}