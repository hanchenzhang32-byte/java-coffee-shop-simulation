package org.uob.a2.parser;

import org.uob.a2.engine.Engine;

public class Parser {

    public Command parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new TickCommand();
        }

        String[] parts = input.trim().split("\\s+");
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "tick": return new TickCommand();
            case "help": return new HelpCommand();
            case "quit": return new QuitCommand();
            case "info": return new InfoCommand();
            case "graph": return new GraphCommand();
            case "save": return new SaveCommand(parts);
            case "load": return new LoadCommand(parts);
            case "build": return new BuildCommand(parts);
            case "cheat": return new CheatCommand(parts);
            default: return new InvalidCommand();
        }
    }

    public String handleCommand(String input, Engine engine) {
        Command c = parse(input);
        return c.execute(new org.uob.a2.engine.Context(engine, engine.getState()));
    }
}
