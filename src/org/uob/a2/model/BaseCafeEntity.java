package org.uob.a2.model;

import org.uob.a2.engine.Tickable;
import org.uob.a2.engine.Context;

public abstract class BaseCafeEntity implements Tickable {

    protected String name;

    public BaseCafeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public abstract void tick(Context ctx);
}
