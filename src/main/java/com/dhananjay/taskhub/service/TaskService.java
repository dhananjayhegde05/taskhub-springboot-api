package com.dhananjay.taskhub.service;

import com.dhananjay.taskhub.dto.TaskRequestDTO;
import com.dhananjay.taskhub.dto.TaskResponseDTO;
import com.dhananjay.taskhub.entity.Task;
import com.dhananjay.taskhub.exception.TaskNotFoundException;
import com.dhananjay.taskhub.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {

      Task task = convertToEntity(taskRequestDTO);
  
      Task savedTask = taskRepository.save(task);
  
      return convertToResponseDTO(savedTask);
    }

    public List<TaskResponseDTO> getAllTasks() {
      return taskRepository.findAll()
      .stream()
      .map(this::convertToResponseDTO)
      .toList();
    }

    public List<TaskResponseDTO> searchTasksByTitle(String title) {

      return taskRepository.findByTitleContainingIgnoreCase(title)
              .stream()
              .map(this::convertToResponseDTO)
              .toList();
    }

    public TaskResponseDTO getTaskById(Long id) {

    Task task = taskRepository.findById(id)
            .orElseThrow(() ->
                    new TaskNotFoundException(
                            "Task with id " + id + " not found"));

      return convertToResponseDTO(task);
      }

      public TaskResponseDTO updateTask(Long id, TaskRequestDTO updatedTask) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task with id " + id + " not found"));
    
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setCreatedAt(updatedTask.getCreatedAt());
    
        Task savedTask = taskRepository.save(existingTask);
    
        return convertToResponseDTO(savedTask);
      }

      public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task with id " + id + " not found"));
    
        taskRepository.delete(task);
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

      private Task convertToEntity(TaskRequestDTO dto) {

        Task task = new Task();
    
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setCreatedAt(dto.getCreatedAt());
    
        return task;
      }

      public List<TaskResponseDTO> getTasksWithPagination(
        int page,
        int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<Task> taskPage =
            taskRepository.findAll(pageable);

    return taskPage.getContent()
            .stream()
            .map(this::convertToResponseDTO)
            .toList();
      }
      
      public List<TaskResponseDTO> getTasksSorted(String sortBy) {

        return taskRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
      }
}
