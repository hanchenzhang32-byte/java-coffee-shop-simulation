package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

public abstract class Consumer extends Entity {

    protected ResourceType product;
    protected int amount;

    public Consumer(String name, ResourceType product, int amount) {
        super(name);
        this.product = product;
        this.amount = amount;
    }

    public ResourceType getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public abstract void consume(Context ctx);

    @Override
    public void tick(Context ctx) {
        consume(ctx);
    }

    public abstract String toCSV();
}
