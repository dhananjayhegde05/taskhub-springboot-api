package com.dhananjay.taskhub.service;

import com.dhananjay.taskhub.dto.TaskRequestDTO;
import com.dhananjay.taskhub.dto.TaskResponseDTO;
import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.dhananjay.taskhub.dto.TaskRequestDTO;
import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {

      Task task = new Task();
  
      task.setTitle(taskRequestDTO.getTitle());
      task.setDescription(taskRequestDTO.getDescription());
      task.setStatus(taskRequestDTO.getStatus());
      task.setCreatedAt(taskRequestDTO.getCreatedAt());
  
      Task savedTask = taskRepository.save(task);
  
      TaskResponseDTO responseDTO = new TaskResponseDTO();
  
      responseDTO.setId(savedTask.getId());
      responseDTO.setTitle(savedTask.getTitle());
      responseDTO.setDescription(savedTask.getDescription());
      responseDTO.setStatus(savedTask.getStatus());
      responseDTO.setCreatedAt(savedTask.getCreatedAt());
  
      return responseDTO;
    }
    public List<TaskResponseDTO> getAllTasks() {
      return taskRepository.findAll()
      .stream()
      .map(this::convertToResponseDTO)
      .toList();
    }
    public TaskResponseDTO getTaskById(Long id) {

      Task task = taskRepository.findById(id).orElse(null);
  
      if (task == null) {
          return null;
      }
  
      return convertToResponseDTO(task);
  }

    public Task updateTask(Long id, Task updatedTask) {

      Task existingTask = taskRepository.findById(id)
              .orElse(null);
  
      if (existingTask == null) {
          return null;
      }
  
      existingTask.setTitle(updatedTask.getTitle());
      existingTask.setDescription(updatedTask.getDescription());
      existingTask.setStatus(updatedTask.getStatus());
      existingTask.setCreatedAt(updatedTask.getCreatedAt());
  
      return taskRepository.save(existingTask);
      }

      public void deleteTask(Long id) {
        taskRepository.deleteById(id);
      }
      private TaskResponseDTO convertToResponseDTO(Task task) {

        TaskResponseDTO responseDTO = new TaskResponseDTO();
    
        responseDTO.setId(task.getId());
        responseDTO.setTitle(task.getTitle());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setStatus(task.getStatus());
        responseDTO.setCreatedAt(task.getCreatedAt());
    
        return responseDTO;
      }
}
