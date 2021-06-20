package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;

// JpaRepositoryを継承する場合は省略可能
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
