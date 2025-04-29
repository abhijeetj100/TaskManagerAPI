package com.abhijeetjain.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }
}
