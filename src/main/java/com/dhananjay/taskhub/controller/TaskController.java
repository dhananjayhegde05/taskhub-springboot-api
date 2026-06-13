package com.dhananjay.taskhub.controller;

import com.dhananjay.taskhub.dto.TaskRequestDTO;
import com.dhananjay.taskhub.dto.TaskResponseDTO;
import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.service.TaskService;
import com.dhananjay.taskhub.dto.TaskRequestDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
      return taskService.createTask(taskRequestDTO);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
      return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id){
      return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task task) {

      return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {

    taskService.deleteTask(id);

      return "Task deleted successfully";
    }
}