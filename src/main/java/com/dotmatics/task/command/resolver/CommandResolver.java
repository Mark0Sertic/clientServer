package com.dotmatics.task.command.resolver;

import com.dotmatics.task.command.ChangeDir;
import com.dotmatics.task.command.Command;
import com.dotmatics.task.command.ListDir;
import com.dotmatics.task.command.Man;
import com.dotmatics.task.statistics.Statistics;

import java.util.*;

// Logic for command resolving from client command message
public class CommandResolver {
    private Map<String, Command> supportedCommands;
    private String currentDir;
    private final String baseDir;
    private CommandContext commandContext;
    private Statistics stat;

    public CommandResolver(String baseDir, Statistics stat) {
        this.currentDir = baseDir;
        this.baseDir = baseDir;
        this.supportedCommands = new HashMap<>();
        this.supportedCommands.put("cd", new ChangeDir());
        this.supportedCommands.put("ls", new ListDir());
        this.supportedCommands.put("man", new Man());
        this.supportedCommands.put("quit", null);
        this.commandContext = new CommandContext(baseDir,baseDir);
        this.stat = stat;
    }

    private boolean isSupportedCommand(String key) {
        return this.supportedCommands.containsKey(key);
    }

    private List<String> removeFirst(List<String> list) {
        if(list.size() <= 1) return new ArrayList<>();
        return list.subList(1, list.size());
    }

    public String executeCommand(String command) {
        List<String> clientCommand = Arrays.asList(command.split("\\s"));
        if(!this.isSupportedCommand(clientCommand.get(0))) {
            return "Unsupported command";
        } else if ("quit".equals(clientCommand.get(0))) {
            return "quit";
        } else {
            commandContext.setCommandParams(removeFirst(clientCommand));
            String result = this.supportedCommands.get(clientCommand.get(0)).execute(this.currentDir, commandContext);
            this.currentDir = commandContext.getCurrentDir();
            this.stat.increaseNumberOfOperations();
            return result;
        }
    }
}
