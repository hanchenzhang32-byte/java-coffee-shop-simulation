package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class CoffeeWaterPump extends BaseCafeEntity {

    public CoffeeWaterPump() {
        super("water-pump");
    }

    
    public void tick(Context ctx) {
        ctx.getState().addCafeResource(CafeResourceType.CAFE_WATER, 1);
    }
}
