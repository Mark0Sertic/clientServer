package com.dotmatics.task.command.resolver;

import java.util.List;

// Context of directory and params for each command
public class CommandContext {
    private final String basPath;
    private String currentDir;
    private List<String> commandParams;

    public CommandContext(String currentDir, String basPath) {
        this.currentDir = currentDir;
        this.basPath = basPath;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }

    public void setCommandParams(List<String> commandParams) {
        this.commandParams = commandParams;
    }

    public String getBasPath() {
        return basPath;
    }
}
