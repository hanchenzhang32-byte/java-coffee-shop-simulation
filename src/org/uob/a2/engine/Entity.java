package org.uob.a2.engine;

public abstract class Entity implements Tickable {

    protected String name;

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
