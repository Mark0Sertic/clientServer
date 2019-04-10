package com.dotmatics.task.command;

import com.dotmatics.task.command.resolver.CommandContext;

import java.io.File;
import java.io.IOException;

// cd command
public class ChangeDir implements Command {
    public ChangeDir() { }

    @Override
    public String execute(String currentDir, CommandContext commandContext) {
        if(commandContext.getCommandParams().size() != 1) return "Wrong format command";

        final File folder = new File(commandContext.getCurrentDir() +"/" +
                commandContext.getCommandParams().get(0));
        StringBuilder result = new StringBuilder();
        String cannonicalPath;

        if(!folder.canExecute()) {
            result.append("Cannot execute cd ");
            result.append(commandContext.getCommandParams().get(0));
            return result.toString();
        }

        try {
            cannonicalPath = folder.getCanonicalPath();
            if(cannonicalPath.contains(commandContext.getBasPath())) {
                result.append(cannonicalPath);
                commandContext.setCurrentDir(cannonicalPath);
            }
            else result.append("Cannot cd out of base directory !!!");
        } catch (IOException e) {
            e.printStackTrace();
            result.append("Cannot execute cd ");
            result.append(commandContext.getCommandParams().get(0));
        } finally {
            return result.toString();
        }
    }
}
