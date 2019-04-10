package com.dotmatics.task.command;

import com.dotmatics.task.command.resolver.CommandContext;

public interface Command {
    String execute(String currentDir, CommandContext commandContext);
}
