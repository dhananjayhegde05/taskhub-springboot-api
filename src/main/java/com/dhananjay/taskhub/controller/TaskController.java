package com.dhananjay.taskhub.controller;

import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.service.TaskService;
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
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
      return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
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