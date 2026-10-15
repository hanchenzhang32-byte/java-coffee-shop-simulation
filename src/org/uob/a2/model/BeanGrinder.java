package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class BeanGrinder extends BaseCafeEntity {

    public BeanGrinder() {
        super("bean-grinder");
    }

    
    public void tick(Context ctx) {
        ctx.getState().addCafeResource(CafeResourceType.CAFE_BEANS, 2);
    }
}
