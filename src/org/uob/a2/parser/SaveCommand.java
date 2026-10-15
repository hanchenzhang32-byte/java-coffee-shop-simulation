package org.uob.a2.parser;

import org.uob.a2.engine.Context;

public class SaveCommand extends Command {

    public SaveCommand(String[] parts) {
        super(parts);
    }

    public String execute(Context ctx) {
        if (words == null || words.size() < 2) {
            return "Usage: save <filename>";
        }
        String filename = words.get(1);
        return ctx.engine().save(filename);
    }
}
