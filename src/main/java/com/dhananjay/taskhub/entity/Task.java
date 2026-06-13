package com.dhananjay.taskhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @NotBlank(message = "Title is required")
   @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
   private String title;

   @NotBlank(message = "Description is required")
   private String description;

   @NotBlank(message = "Status is required")
   private String status;

   @NotBlank(message = "Created date is required")
   private String createdAt;
}