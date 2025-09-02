package com.uninter.tasklist.repositories;

import com.uninter.tasklist.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID>, JpaSpecificationExecutor<TaskModel> {
}
