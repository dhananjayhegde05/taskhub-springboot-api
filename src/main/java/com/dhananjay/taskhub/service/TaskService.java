package com.dhananjay.taskhub.service;

import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
      return taskRepository.findAll();
    }
    public Task getTaskById(Long id) {
      return taskRepository.findById(id).orElse(null);
    }
}
