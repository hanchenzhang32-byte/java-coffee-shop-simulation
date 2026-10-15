package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;

public class CheatCommand extends Command {

    public CheatCommand(String[] parts) {
        super(parts);
    }


    public String execute(Context ctx) {
        if (words == null || words.size() < 3) {
            return "Usage: cheat <resource> <amount>";
        }

        String resName = words.get(1).toUpperCase();
        String amtStr  = words.get(2);

        try {
            ResourceType type = ResourceType.valueOf(resName);
            int amount = Integer.parseInt(amtStr);
            ctx.state().addResource(type, amount);
            return "Cheated " + amount + " " + type;
        } catch (IllegalArgumentException e) {
            return "Invalid cheat command.";
        }
    }
}
