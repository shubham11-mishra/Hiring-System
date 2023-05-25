package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.dto.CommentsDTO;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;
import com.lexisnexis.hiring.exception.NoCommentsFoundException;

import java.util.List;

public interface CommentService {

    public Comments createComment(Comments comment) ;

    public List<CommentsDTO> getComments();

    public CommentsDTO getCommentsByCommentId(int commentId) ;

    public List<CommentsDTO> getCommentsByEmployeeId(int employeeId) ;

    public List<CommentsDTO> getCommentsByCandidateId(int candidateId);

    public Comments updateComment(Comments comment, int commentId) ;

    public void deleteComment(int commentId) throws CommentIdNotFoundException;
}