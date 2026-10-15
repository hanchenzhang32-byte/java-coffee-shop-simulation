package org.uob.a2.parser;

import org.uob.a2.engine.Context;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super();
    }

 
    public String execute(Context ctx) {
        return "Invalid command.";
    }
}
