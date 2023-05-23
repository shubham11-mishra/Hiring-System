package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.comment_Id = ?1", nativeQuery = true)
	public Comments findByCommentId(int comment_id);
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.employee_employee_Id = ?1", nativeQuery = true)
	public List<Comments> findByEmpId(int employee_id);
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.candidate_candidate_Id = ?1", nativeQuery = true)
	public List<Comments> findByCandidId(int candidate_id);
	
//	Comments findByCommentId(int commentId);
}
