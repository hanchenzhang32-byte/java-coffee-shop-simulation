package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;

import java.util.List;
import java.util.Map;

public class GraphCommand extends Command {

    public GraphCommand() {
        super();
    }

    public GraphCommand(String[] parts) {
        super(parts);
    }

   
    public String execute(Context ctx) {
        ResourceType type = ResourceType.CREDITS;

        if (words != null && words.size() >= 2) {
            try {
                type = ResourceType.valueOf(words.get(1).toUpperCase());
            } catch (IllegalArgumentException e) {
                
            }
        }

        
        List<Map<ResourceType, Integer>> history = ctx.state().getHistory();

        StringBuilder sb = new StringBuilder();
        int tick = 0;
        for (Map<ResourceType, Integer> snap : history) {
            int value = snap.get(type);
            sb.append(String.format("%3d: ", tick++));
            for (int i = 0; i < value; i += 10) {
                sb.append("*");
            }
            sb.append(" (").append(value).append(")\n");
        }
        return sb.toString();
    }
}
