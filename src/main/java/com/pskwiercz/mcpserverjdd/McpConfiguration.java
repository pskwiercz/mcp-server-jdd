package com.pskwiercz.mcpserverjdd;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpConfiguration {

    @Bean
    public ToolCallbackProvider tools(TaskTools taskTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(taskTools)
                .build();
    }




    @Bean
    public List<McpServerFeatures.SyncPromptSpecification> gamePrompts() {
        var playerCountPrompt = new McpSchema.Prompt(
                "get_task_by_id", "Find task by ID",
                List.of(new McpSchema.PromptArgument(
                        "id", "Task ID", true)));

        var playerCountPromptSpec = new McpServerFeatures.SyncPromptSpecification(
                playerCountPrompt, (exchange
                , getPromptRequest) -> {
            String playerCount =
                    (String) getPromptRequest.arguments().get("get_task_by_id");
            var userMessage = new McpSchema.PromptMessage(
                    McpSchema.Role.USER,
                    new McpSchema.TextContent(String.format("Find task for id %s", playerCount)));

            return new McpSchema.GetPromptResult(
                    String.format("A prompt to find task for id %s", playerCount),
                    List.of(userMessage));
        });

        return List.of(playerCountPromptSpec);
    }
}
