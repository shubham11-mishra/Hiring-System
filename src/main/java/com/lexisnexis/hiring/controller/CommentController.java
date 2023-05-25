package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.CommentsDTO;
import com.lexisnexis.hiring.entity.Comments;
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
    CommentService commentService;
    @Autowired
    CandidateService candidateService;

    @PostMapping(value = "/createcomment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createComment(@RequestBody Comments comment) {
        Comments newComment = commentService.createComment(comment);
        return new ResponseEntity<String>(comment.getComments()+"Comment Added Successfully ", HttpStatus.OK);
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<CommentsDTO>> getComments() {
        List<CommentsDTO> commentList = commentService.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByEmpId/{employeeId}")
    public ResponseEntity<List<CommentsDTO>> getCommentsByEmpId(@PathVariable(value = "employeeId") int employeeId){
        List<CommentsDTO> comment = commentService.getCommentsByEmployeeId(employeeId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByCandidId/{candidateID}")
    public ResponseEntity<List<CommentsDTO>> getCommentsByCandidId(@PathVariable(value = "candidateID") int candidateID) {
        List<CommentsDTO> comment = commentService.getCommentsByCandidateId(candidateID);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/getCommentsByCommentId/{commentId}")
    public ResponseEntity<CommentsDTO> getCommentsById(@PathVariable(value = "commentId") int commentId)
    {
        CommentsDTO comment = commentService.getCommentsByCommentId(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<String> updateComment(@RequestBody Comments comment,
                                                  @PathVariable(value = "commentId") int commentId) {
        Comments updateComment = commentService.updateComment(comment, commentId);
        return new ResponseEntity<>(updateComment.getComments() +" Comment Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComments(@PathVariable(value = "commentId") int commentId)
    {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comments deleted successfully deleted!", HttpStatus.OK);
    }
}