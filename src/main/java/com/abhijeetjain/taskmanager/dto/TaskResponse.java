package com.abhijeetjain.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
