package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.advice.APIResponse;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdFoundException;
import com.lexisnexis.hiring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments1")
public class CommentController {

	@Autowired
	CommentService commentlogic;

	@PostMapping(value = "/createcomment", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Comments> createComent(@RequestBody Comments c) {
		Comments newComments = commentlogic.createComment(c);
		return new ResponseEntity<>(newComments, HttpStatus.OK);
	}

	@GetMapping("/getComments")
	public ResponseEntity<List<Comments>> getComments() {
		List<Comments> commentsList = commentlogic.getComments();
		return new ResponseEntity<List<Comments>>(commentsList, HttpStatus.OK);
	}

	@GetMapping("/getCommentsByEmpId/{employee_id}")
	public ResponseEntity<Comments> getCommentsByEmpId(@PathVariable(value = "employee_id") int employee_id) {
		Comments comment = commentlogic.getCommentsByEmployeeId(employee_id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@GetMapping("/getCommentsByCandidId/{candidate_id}")
	public ResponseEntity<Comments> getCommentsByCandidId(@PathVariable(value = "candidate_id") int candidate_id) {
		Comments comment = commentlogic.getCommentsByCandidateId(candidate_id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@GetMapping("/getCommentsByCommentId/{comment_id}")
	public ResponseEntity<Comments> getCommentsById(@PathVariable(value = "comment_id") int comment_id)
			throws CommentIdFoundException {
		Comments comment = commentlogic.getCommentsByCommentId(comment_id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@PutMapping("/updateComment/{comment_id}")
	public ResponseEntity<APIResponse> updateComment(Comments comment,
			@PathVariable(value = "comment_id") int comment_id) {
		APIResponse updateComment = commentlogic.updateComment(comment, comment_id);
		return new ResponseEntity<APIResponse>(updateComment, HttpStatus.OK);

	}

	@DeleteMapping("/deleteComment/{comment_id}")
	public ResponseEntity<String> deleteComments(@PathVariable(value = "comment_id") int comment_id)
			throws CommentIdFoundException {
		commentlogic.deleteComment(comment_id);
		return new ResponseEntity<>("Comments deleted successfully deleted!", HttpStatus.OK);
	}
}
