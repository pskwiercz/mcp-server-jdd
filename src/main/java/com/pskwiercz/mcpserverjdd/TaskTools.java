package com.pskwiercz.mcpserverjdd;

import com.pskwiercz.mcpserverjdd.model.Task;
import com.pskwiercz.mcpserverjdd.model.Type;
import com.pskwiercz.mcpserverjdd.repository.TaskRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskTools {

    private final TaskRepository taskRepository;

    public TaskTools(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Tool(name = "get_task_by_id", description = "Find task by ID")
    public Task getTaskById(
            @ToolParam(description = "Task ID") Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Tool(name = "get_all_tasks_by_type",description = "Find all tasks by type")
    public List<Task> getTaskByType(
            @ToolParam(description = "Type") Type type) {
        return taskRepository.findByType(type);
    }

    @Tool(name = "get_all_tasks",description = "Get all tasks")
    public List<Task> getAllTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
