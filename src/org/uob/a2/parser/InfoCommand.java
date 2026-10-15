package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;

import java.util.Map;

public class InfoCommand extends Command {

    public InfoCommand() {
        super();
    }

   
    public String execute(Context ctx) {
        Map<ResourceType, Integer> inv = ctx.state().getInventorySnapshot();
        StringBuilder sb = new StringBuilder();
        sb.append("Resources:\n");
        for (ResourceType t : ResourceType.values()) {
            sb.append("  ").append(t).append(": ").append(inv.get(t)).append("\n");
        }
        return sb.toString();
    }
}
