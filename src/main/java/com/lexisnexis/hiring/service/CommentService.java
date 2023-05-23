package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;
import com.lexisnexis.hiring.exception.NoCommentsFoundException;

import java.util.List;

public interface CommentService {

    public Comments createComment(Comments comment) ;

    public List<Comments> getComments() throws NoCommentsFoundException;

    public Comments getCommentsByCommentId(int commentId) throws CommentIdNotFoundException;

    public List<Comments> getCommentsByEmployeeId(int employeeId) throws NoCommentsFoundException;

    public List<Comments> getCommentsByCandidateId(int candidateId) throws NoCommentsFoundException;

    public Comments updateComment(Comments comment, int commentId) throws CommentIdNotFoundException;

    public void deleteComment(int commentId) throws CommentIdNotFoundException;
}