package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

public abstract class Converter extends Entity {

    protected ResourceType input;
    protected int inputAmount;

    protected ResourceType output;
    protected int outputAmount;

    public Converter(String name,
                     ResourceType input, int inputAmount,
                     ResourceType output, int outputAmount) {
        super(name);
        this.input = input;
        this.inputAmount = inputAmount;
        this.output = output;
        this.outputAmount = outputAmount;
    }

    public ResourceType getInput() {
        return input;
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public ResourceType getOutput() {
        return output;
    }

    public int getOutputAmount() {
        return outputAmount;
    }

    public abstract void convert(Context ctx);

    @Override
    public void tick(Context ctx) {
        convert(ctx);
    }

    public abstract String toCSV();
}
