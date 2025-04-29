package com.abhijeetjain.taskmanager.repository;

import com.abhijeetjain.taskmanager.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    // Custom query methods can be defined here if needed

}
