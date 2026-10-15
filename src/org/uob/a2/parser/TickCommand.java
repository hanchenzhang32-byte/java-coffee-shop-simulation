package org.uob.a2.parser;

import org.uob.a2.engine.Context;

import java.util.List;

public class TickCommand extends Command {

    public TickCommand() {
        super();
    }

    public TickCommand(List<String> words) {
        super(words);
    }

    public TickCommand(String[] parts) {
        super(parts);
    }


    public String execute(Context ctx) {
        ctx.engine().nextTick();
        return "Tick " + ctx.engine().getCurrentTick();
    }
}
