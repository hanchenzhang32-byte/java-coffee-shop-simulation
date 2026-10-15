package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class LatteMachine extends BaseCafeEntity {

    public LatteMachine() {
        super("latte-machine");
    }

    
    public void tick(Context ctx) {
        boolean hasEsp = ctx.getState().removeCafeResource(CafeResourceType.ESPRESSO, 1);
        boolean hasMilk = ctx.getState().removeCafeResource(CafeResourceType.CAFE_MILK, 1);

        if (hasEsp && hasMilk) {
            ctx.getState().addCafeResource(CafeResourceType.LATTE, 1);
        }
    }
}
