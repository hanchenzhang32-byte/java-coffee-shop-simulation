package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class EspressoMachine extends BaseCafeEntity {

    public EspressoMachine() {
        super("espresso-machine");
    }

  
    public void tick(Context ctx) {
        boolean hasBeans = ctx.getState().removeCafeResource(CafeResourceType.CAFE_BEANS, 1);
        boolean hasWater = ctx.getState().removeCafeResource(CafeResourceType.CAFE_WATER, 1);

        if (hasBeans && hasWater) {
            ctx.getState().addCafeResource(CafeResourceType.ESPRESSO, 1);
        }
    }
}
