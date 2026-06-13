package com.dhananjay.taskhub.repository;

import com.dhananjay.taskhub.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}