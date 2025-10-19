package com.pskwiercz.mcpserverjdd.repository;

import com.pskwiercz.mcpserverjdd.model.Task;
import com.pskwiercz.mcpserverjdd.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByType(Type type);
}
