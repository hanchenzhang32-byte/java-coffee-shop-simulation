package org.uob.a2.parser;

import org.uob.a2.engine.Context;

public class LoadCommand extends Command {

    public LoadCommand(String[] parts) {
        super(parts);
    }
    public String execute(Context ctx) {
        if (words == null || words.size() < 2) {
            return "Usage: load <filename>";
        }
        String filename = words.get(1);
        return ctx.engine().load(filename);
    }
}
