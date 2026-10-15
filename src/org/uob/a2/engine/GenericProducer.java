package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;


public class GenericProducer extends Producer {

    public GenericProducer(String name, ResourceType product, int amount) {
        super(name, product, amount);
    }

   
    public void produce(Context ctx) {
        // Use getters from Producer/Entity, do not use undefined fields
        ctx.state().addResource(getProduct(), getAmount());
    }

    
    public String toCSV() {
        // Format used when saving:
        // producer,name,PRODUCT,amount
        return "producer," + getName() + "," + getProduct() + "," + getAmount();
    }
}
