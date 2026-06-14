package com.dhananjay.taskhub.controller;

import com.dhananjay.taskhub.dto.TaskRequestDTO;
import com.dhananjay.taskhub.dto.TaskResponseDTO;
import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.service.TaskService;
import com.dhananjay.taskhub.dto.TaskRequestDTO;
import com.dhananjay.taskhub.dto.ApiResponse;
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
    public ApiResponse<TaskResponseDTO> createTask(
        @Valid @RequestBody TaskRequestDTO taskRequestDTO) {

    TaskResponseDTO responseDTO = taskService.createTask(taskRequestDTO);

    return new ApiResponse<>(
            true,
            "Task created successfully",
            responseDTO
    );
    }

    @GetMapping
    public ApiResponse<List<TaskResponseDTO>> getAllTasks() {

    List<TaskResponseDTO> tasks = taskService.getAllTasks();

    return new ApiResponse<>(
            true,
            "Tasks fetched successfully",
            tasks
    );
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id){
      return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponseDTO> updateTask(
        @PathVariable Long id,
        @Valid @RequestBody TaskRequestDTO taskRequestDTO) {

    TaskResponseDTO responseDTO =
            taskService.updateTask(id, taskRequestDTO);

    return new ApiResponse<>(
            true,
            "Task updated successfully",
            responseDTO
    );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTask(@PathVariable Long id) {

    taskService.deleteTask(id);

    return new ApiResponse<>(
            true,
            "Task deleted successfully",
            null
    );
}
}