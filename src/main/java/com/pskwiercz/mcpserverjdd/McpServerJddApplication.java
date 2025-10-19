package com.pskwiercz.mcpserverjdd;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerJddApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerJddApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(TaskTools taskTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(taskTools)
                .build();
    }
}
