package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;
import com.lexisnexis.hiring.exception.NoCommentsFoundException;
import com.lexisnexis.hiring.service.CandidateService;
import com.lexisnexis.hiring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentlogic;
    @Autowired
    CandidateService  candidateService;


    @PostMapping(value = "/createcomment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        Comments newComment = commentlogic.createComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }
    @GetMapping("/getComments")
    public ResponseEntity<List<Comments>> getComments() throws NoCommentsFoundException {
        List<Comments> commentList = commentlogic.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByEmpId/{employeeId}")
    public ResponseEntity<List<Comments>> getCommentsByEmpId(@PathVariable(value = "employeeId") int employeeId) throws NoCommentsFoundException {
        List<Comments> comment = commentlogic.getCommentsByEmployeeId(employeeId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByCandidId/{employeeId}")
    public ResponseEntity<List<Comments>> getCommentsByCandidId(@PathVariable(value = "employeeId") int employeeId) throws NoCommentsFoundException {
        List<Comments> comment = commentlogic.getCommentsByCandidateId(employeeId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByCommentId/{commentId}")
    public ResponseEntity<Comments> getCommentsById(@PathVariable(value = "commentId") int commentId)
            throws CommentIdNotFoundException {
        Comments comment = commentlogic.getCommentsByCommentId(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comment,
                                                  @PathVariable(value = "commentId") int commentId) throws CommentIdNotFoundException {
        Comments updateComment = commentlogic.updateComment(comment, commentId);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComments(@PathVariable(value = "commentId") int commentId)
            throws CommentIdNotFoundException {
        commentlogic.deleteComment(commentId);
        return new ResponseEntity<>("Comments deleted successfully deleted!", HttpStatus.OK);
    }
}