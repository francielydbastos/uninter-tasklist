package com.uninter.tasklist.services;

import com.uninter.tasklist.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    TaskModel save(TaskModel taskModel);

    TaskModel update(UUID id, TaskModel updatedTaskModel);

    List<TaskModel> findAll();

    Optional<TaskModel> findById(UUID id);

    void delete(TaskModel taskModel);

}
