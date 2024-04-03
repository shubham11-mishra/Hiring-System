package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.dto.CommentsDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.exception.CommentIdNotFoundException;
import com.lexisnexis.hiring.exception.NoCommentsFoundException;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        } else {
            System.out.println(comment.getComment());
            Candidate candidate = candidateRepository.findById(comment.getCandidate().getCandidateId()).get();
            candidate.setResult(comment.getResult());
            if (comment.getResult().equalsIgnoreCase("L1Selected")) {
                candidate.setLevel1Date(LocalDateTime.now());
            }
            if (comment.getResult().equalsIgnoreCase("L2Selected")) {
                candidate.setLevel2Date(LocalDateTime.now());
            }
            candidateRepository.save(candidate);
            return commentsRepository.save(comment);
        }
    }

    @Override
    public List<CommentsDTO> getComments() throws NoCommentsFoundException {
//        if (commentsRepository.findAll().size() == 0) {
//            throw new CommentIdNotFoundException("Comment id incorrect");
//        } else {
            List<CommentsDTO> commentsDTOS = new ArrayList<>();
            for (Comments comment : commentsRepository.findAll()) {
                if (comment != null) {
                    commentsDTOS.add(new CommentsDTO(comment.getResult(), comment.getComment()));
                }
            }
            return commentsDTOS;
//        }
    }


    @Override
    public CommentsDTO getCommentsByCommentId(int commentId) {
        if (commentsRepository.findById(commentId) == null) {
            throw new CommentIdNotFoundException("Comment id incorrect");
        } else {
            Comments comments = commentsRepository.findById(commentId).get();
            return new CommentsDTO(comments.getResult(), comments.getComment());
        }
    }

    @Override
    public List<CommentsDTO> getCommentsByEmployeeId(int employeeId) throws NoCommentsFoundException {
//        if (commentsRepository.findByEmpId(employeeId).size() == 0) {
//            throw new NoCommentsFoundException("No Comments found with that Employee ID");
//        } else {
            List<CommentsDTO> commentsDTOS = new ArrayList<>();
            for (Comments comment : commentsRepository.findByEmpId(employeeId)) {
                if (comment != null) {
                    commentsDTOS.add(new CommentsDTO(comment.getResult(), comment.getComment()));
                }
            }
            return commentsDTOS;
//        }
    }


    @Override
    public List<CommentsDTO> getCommentsByCandidateId(int candidateId) throws NoCommentsFoundException {
//        if (commentsRepository.findByCandidId(candidateId).size() == 0) {
//            throw new NoCommentsFoundException("NO Comments found with that Candidate id");
//        } else {
            List<CommentsDTO> commentsDTOS = new ArrayList<>();
            for (Comments comment : commentsRepository.findByCandidId(candidateId)) {
                if (comment != null) {
                    commentsDTOS.add(new CommentsDTO(comment.getResult(), comment.getComment()));
                }
            }
            return commentsDTOS;
//        }
    }

    @Override
    public Comments updateComment(Comments comment, int commentId) throws CommentIdNotFoundException {
        Comments existingComment = commentsRepository.findById(commentId).get();
        if (commentsRepository.findById(commentId).isEmpty()) {
            if (comment.getResult() != null) {
                existingComment.setResult(comment.getResult());
            }
            if (comment.getComment() != null) {
                existingComment.setComment(comment.getComment());
            }
            Candidate candidate = candidateRepository.findById(comment.getCandidate().getCandidateId()).get();
            candidate.setResult(comment.getResult());
            candidateRepository.save(candidate);
            return commentsRepository.save(existingComment);
        } else {
            throw new CommentIdNotFoundException("Invalid comment id");
        }
    }

    @Override
    public void deleteComment(int commentId) throws CommentIdNotFoundException {
        Comments comment = commentsRepository.findById(commentId).get();
        if (commentsRepository.findById(commentId).isEmpty()) {
            commentsRepository.deleteById(commentId);
        } else {
            throw new CommentIdNotFoundException("comment id not found" + " " + commentId);
        }
    }
}