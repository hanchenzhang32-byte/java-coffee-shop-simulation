package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;


public class GenericConverter extends Converter {

    public GenericConverter(String name,
                            ResourceType input,
                            int inputAmount,
                            ResourceType output,
                            int outputAmount) {
        super(name, input, inputAmount, output, outputAmount);
    }

    @Override
    public void convert(Context ctx) {
        // First try to remove the input
        boolean ok = ctx.state().removeResource(getInput(), getInputAmount());
        if (ok) {
            // Only if we successfully removed the input, we add the output
            ctx.state().addResource(getOutput(), getOutputAmount());
        }
    }

    @Override
    public String toCSV() {
        // Format:
        // converter,name,input,inputAmount,output,outputAmount
        return "converter," + getName() + ","
                + getInput() + "," + getInputAmount() + ","
                + getOutput() + "," + getOutputAmount();
    }
}
