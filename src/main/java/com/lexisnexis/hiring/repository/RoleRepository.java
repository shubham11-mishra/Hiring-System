package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByDesignation(String designation);

}
