package com.dotmatics.task.command;

import com.dotmatics.task.command.resolver.CommandContext;

// Man command also added as addition
public class Man implements Command {
    public Man() {};

    @Override
    public String execute(String currentDir, CommandContext commandContext) {
        if(commandContext.getCommandParams().size() != 0) return "Wrong format command";
        String out = "Operations supported: \n" +
                "cd <dir> \n" +
                "ls \n";

        return out;
    }
}
