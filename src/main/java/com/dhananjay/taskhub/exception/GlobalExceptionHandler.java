package com.dhananjay.taskhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.dhananjay.taskhub.dto.ApiResponse;
import com.dhananjay.taskhub.exception.TaskNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleTaskNotFoundException(
          TaskNotFoundException ex) {
  
      ApiResponse<String> response = new ApiResponse<>(
              false,
              ex.getMessage(),
              null
      );
  
      return new ResponseEntity<>(
              response,
              HttpStatus.NOT_FOUND
      );
  }
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return new ResponseEntity<>(
                errorMessage,
                HttpStatus.BAD_REQUEST
        );
    }
}