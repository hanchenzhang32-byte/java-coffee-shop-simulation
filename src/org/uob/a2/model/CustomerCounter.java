package org.uob.a2.model;

import org.uob.a2.engine.Context;

public class CustomerCounter extends BaseCafeEntity {

    public CustomerCounter() {
        super("customer-counter");
    }

    
    public void tick(Context ctx) {
        boolean bought = ctx.getState().removeCafeResource(CafeResourceType.LATTE, 1);

        if (bought) {
            ctx.getState().addCafeResource(CafeResourceType.CUSTOMERS, 1);
        }
    }
}
