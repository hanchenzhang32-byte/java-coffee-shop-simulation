package org.uob.a2.parser;

import org.uob.a2.engine.Context;

import java.util.Arrays;
import java.util.List;


public abstract class Command {

    protected List<String> words;

    public Command(List<String> words) {
        this.words = words;
    }

    public Command() {
        this.words = null;
    }

    protected Command(String[] parts) {
        this(Arrays.asList(parts));
    }


    public abstract String execute(Context ctx);
}
