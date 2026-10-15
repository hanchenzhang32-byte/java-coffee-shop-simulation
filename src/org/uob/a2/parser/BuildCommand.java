package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class BuildCommand extends Command {

    private String[] parts;

    public BuildCommand(String[] parts) {
        this.parts = parts;
    }

    @Override
    public String execute(Context ctx) {

        if (parts.length < 2) {
            return "Usage: build <type>";
        }

        String type = parts[1].toLowerCase();

        switch (type) {

            // ===== Mine (producer for ORE) =====
            case "mine":
                ctx.state().addProducer(
                        new GenericProducer("mine", ResourceType.ORE, 5)
                );
                return "Built mine";

            // ===== Generator (producer for CREDITS) =====
            case "generator":
                ctx.state().addProducer(
                        new GenericProducer("generator", ResourceType.CREDITS, 20)
                );
                return "Built generator";

           
            case "printer":
                ctx.state().addProducer(
                        new GenericProducer("printer", ResourceType.METAL, 3)
                );
                return "Built printer";

           
            case "refinery":
                ctx.state().addConverter(
                        new GenericConverter(
                                "refinery",
                                ResourceType.ORE, 2,
                                ResourceType.METAL, 1
                        )
                );
                return "Built refinery";

        
            case "port":
                ctx.state().addConsumer(
                        new GenericConsumer("port", ResourceType.METAL, 1)
                );
                return "Built port";

            default:
                return "Unknown build type: " + type;
        }
    }
}
