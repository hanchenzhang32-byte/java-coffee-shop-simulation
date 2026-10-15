package org.uob.a2.parser;

import org.uob.a2.engine.Context;

public class QuitCommand extends Command {

    public QuitCommand() {
        super();
    }
    public String execute(Context ctx) {
      
        return "quit";
    }
}
