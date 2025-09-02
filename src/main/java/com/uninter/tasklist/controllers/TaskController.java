package com.uninter.tasklist.controllers;

import com.uninter.tasklist.models.TaskModel;
import com.uninter.tasklist.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id).get());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskModel taskModel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to create task: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable UUID id, @RequestBody TaskModel updatedTask) {
        try {
            var updatedTaskResult = taskService.update(id, updatedTask);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTaskResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update task: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable UUID id) {
        try {
            taskService.delete(taskService.findById(id).get());
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete task: "+ e.getMessage());
        }
    }
}


