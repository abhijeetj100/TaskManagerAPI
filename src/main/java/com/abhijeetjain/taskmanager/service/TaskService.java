package com.abhijeetjain.taskmanager.service;

import com.abhijeetjain.taskmanager.dto.TaskRequest;
import com.abhijeetjain.taskmanager.dto.TaskResponse;
import com.abhijeetjain.taskmanager.entity.TaskEntity;
import com.abhijeetjain.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Add methods to handle task operations, such as create, update, delete, and retrieve tasks
    public TaskResponse createTask(TaskRequest request){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(request.getTitle());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setCompleted(false);

        TaskEntity savedTask = taskRepository.save(taskEntity);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.isCompleted(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt()
        );
    }

    public List<TaskResponse> getAllTasks(){
        List<TaskEntity> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted(),
                        task.getCreatedAt(),
                        task.getUpdatedAt()
                ))
                .toList();
//        .collect(Collections.toList());
    }

    public TaskResponse getTaskById(String id){
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public TaskResponse updateTask(String id, TaskRequest request){
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        TaskEntity savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.isCompleted(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt()
        );
    }

    public void deleteTask(String id){
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        taskRepository.delete(task);
    }
}
