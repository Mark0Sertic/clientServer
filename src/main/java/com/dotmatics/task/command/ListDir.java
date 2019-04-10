package com.dotmatics.task.command;

import com.dotmatics.task.command.resolver.CommandContext;

import java.io.File;
import java.util.StringJoiner;

// ls command
public class ListDir implements Command {
    public ListDir()  { }

    @Override
    public String execute(String currentDir, CommandContext commandContext) {
        if(commandContext.getCommandParams().size() != 0) return "Wrong format command";

        final File folder = new File(commandContext.getCurrentDir());
        StringJoiner result = new StringJoiner("\n");
        for (final File fileEntry : folder.listFiles()) {
            result.add(fileEntry.getName());

        }

        return result.toString();
    }
}
