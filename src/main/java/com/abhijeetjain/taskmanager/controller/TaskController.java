package com.abhijeetjain.taskmanager.controller;

import com.abhijeetjain.taskmanager.dto.TaskResponse;
import com.abhijeetjain.taskmanager.dto.TaskRequest;
import com.abhijeetjain.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    // Define your endpoints here

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request){
        TaskResponse createdTask = taskService.createTask(request);
        return ResponseEntity
                .created(URI.create("/tasks/" + createdTask.getId()))
                .body(createdTask);
// OR
//        return ResponseEntity.status(201).body(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskByid(@PathVariable String id){
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String id, @Valid @RequestBody TaskRequest request){
        TaskResponse updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
