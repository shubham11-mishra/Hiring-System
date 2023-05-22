package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.comment_id = ?1", nativeQuery = true)
	public Comments findByCommentId(int comment_id);
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.employee_id = ?1", nativeQuery = true)
	public Comments findByEmpId(int employee_id);
	
	@Query(value = "SELECT * FROM comments_table c WHERE c.candidate_id = ?1", nativeQuery = true)
	public Comments findByCandidId(int candidate_id);
}
