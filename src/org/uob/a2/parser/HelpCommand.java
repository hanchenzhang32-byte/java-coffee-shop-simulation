package org.uob.a2.parser;

import org.uob.a2.engine.Context;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    public String execute(Context ctx) {
        
        return "Commands: tick, help, quit, info, graph, save <file>, load <file>, "
                + "build <type>, cheat <resource> <amount>";
    }
}
