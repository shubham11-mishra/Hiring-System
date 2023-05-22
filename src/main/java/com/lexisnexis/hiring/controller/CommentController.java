package com.lexisnexis.hiring.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.UserNotFoundException;
import com.lexisnexis.hiring.response.APIResponse;
import com.lexisnexis.hiring.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentlogic;

	@PostMapping("/createcomment")
	public ResponseEntity<APIResponse> createComent(@RequestBody Comments c) throws NoSuchAlgorithmException {
		APIResponse apiResponse = commentlogic.createComment(c);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/getComments")
	public ResponseEntity<List<Comments>> getComments() {
		List<Comments> apiResponse = commentlogic.getComments();
		return new ResponseEntity<List<Comments>>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/getCommentsByEmpId/{employee_id}")
	public ResponseEntity<Comments> getCommentsByEmpId(@PathVariable(value = "employee_id") int employee_id) {
		Comments apiResponse = commentlogic.getCommentsByEmployeeId(employee_id);
		return new ResponseEntity<Comments>(HttpStatus.OK);
	}

	@GetMapping("/getCommentsByCandidId/{candidate_id}")
	public ResponseEntity<Comments> getCommentsByCandidId(@PathVariable(value = "candidate_id") int candidate_id) {
		Comments apiResponse = commentlogic.getCommentsByCandidateId(candidate_id);
		return new ResponseEntity<Comments>(HttpStatus.OK);
	}

//	@GetMapping("/getCommentsById")
//	public ResponseEntity<List<Comments>> getCommentsById(int comment_id) {
//		 Optional<Comments> apiResponse = commentlogic.getCommentsById(comment_id);
//		return new ResponseEntity<List<Comments>>(HttpStatus.OK);
//	}

	@GetMapping("/getCommentsByCommentId/{comment_id}")
	public ResponseEntity<Comments> getCommentsById(@PathVariable(value = "comment_id") int comment_id) {
		Comments apiResponse = commentlogic.getCommentsByCommentId(comment_id);
		return new ResponseEntity<Comments>(HttpStatus.OK);
	}

	@PutMapping("/updateComment/{comment_id}")
	public ResponseEntity<APIResponse> updateComment(Comments comment,
			@PathVariable(value = "comment_id") int comment_id) {
		APIResponse apiResponse = commentlogic.updateComment(comment, comment_id);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/deleteComment/{comment_id}")
	public void deleteComments(@PathVariable(value = "comment_id") int comment_id) throws UserNotFoundException {
		commentlogic.deleteComment(comment_id);
	}
}
