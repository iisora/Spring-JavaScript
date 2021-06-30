package com.dev.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.personal.model.Task;

// REST APIのCRUDを自動生成する
@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Long> {

}
