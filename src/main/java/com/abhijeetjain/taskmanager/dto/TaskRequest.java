package com.abhijeetjain.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description;

}
