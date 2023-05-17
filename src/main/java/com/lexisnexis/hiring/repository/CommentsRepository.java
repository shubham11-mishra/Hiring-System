package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
