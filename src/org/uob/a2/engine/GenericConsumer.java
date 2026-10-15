package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

public class GenericConsumer extends Consumer {

    public GenericConsumer(String name, ResourceType product, int amount) {
        super(name, product, amount);
    }

    @Override
    public void consume(Context ctx) {
        // Try to remove the resource; if not enough, nothing happens
        ctx.state().removeResource(getProduct(), getAmount());
    }

    
    public String toCSV() {
        // Format:
        // consumer,name,PRODUCT,amount
        return "consumer," + getName() + "," + getProduct() + "," + getAmount();
    }
}
