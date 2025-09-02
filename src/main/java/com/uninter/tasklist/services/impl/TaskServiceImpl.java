package com.uninter.tasklist.services.impl;

import com.uninter.tasklist.models.TaskModel;
import com.uninter.tasklist.repositories.TaskRepository;
import com.uninter.tasklist.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    @Override
    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    @Override
    public TaskModel update(UUID id, TaskModel updatedTask) {
        var taskModelOptional = findById(id);
        TaskModel existingTask = taskModelOptional.get();

        if (updatedTask.getName() != null && !updatedTask.getName().equals(existingTask.getName())) {
            existingTask.setName(updatedTask.getName());
        }
        if (updatedTask.getDueDate() != null && !updatedTask.getDueDate().equals(existingTask.getDueDate())) {
            existingTask.setDueDate(updatedTask.getDueDate());
        }
        if (updatedTask.getAssignee() != null && !updatedTask.getAssignee().equals(existingTask.getAssignee())) {
            existingTask.setAssignee(updatedTask.getAssignee());
        }

        return save(existingTask);
    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<TaskModel> findById(UUID id) {
        Optional<TaskModel> taskModelOptional = taskRepository.findById(id);

        if (taskModelOptional.isEmpty()) {
            throw new RuntimeException("Task with id " + id + " not found");
        }

        return taskModelOptional;
    }

    @Override
    public void delete(TaskModel taskModel) {
        taskRepository.delete(taskModel);
    }
}
