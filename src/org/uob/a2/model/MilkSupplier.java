package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class MilkSupplier extends BaseCafeEntity {

    public MilkSupplier() {
        super("milk-supplier");
    }

    
    public void tick(Context ctx) {
        ctx.getState().addCafeResource(CafeResourceType.CAFE_MILK, 1);
    }
}
