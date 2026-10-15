package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class Engine {

    private SimulationState state;
    private int currentTick;

   
    public Engine(SimulationState state) {
        this.state = state;
        this.currentTick = 0;

        // Initial credits for building and testing
        this.state.addResource(ResourceType.CREDITS, 1000);
    }

    public SimulationState getState() {
        return state;
    }

    public int getCurrentTick() {
        return currentTick;
    }

    
    public String nextTick() {
        Context ctx = new Context(this, state);

        // Producers generate resources
        for (Producer p : state.getProducers()) {
            p.tick(ctx);
        }

        // Converters transform input → output
        for (Converter c : state.getConverters()) {
            c.tick(ctx);
        }

        // Consumers remove resources
        for (Consumer c : state.getConsumers()) {
            c.tick(ctx);
        }

        // Save resource snapshot
        state.updateHistory();
        currentTick++;

        return "Tick " + currentTick + " completed.";
    }

    public String save(String filename) {
        File file = new File(filename);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write("CurrentTick," + currentTick + "\n");

            fw.write("[RESOURCES]\n");
            for (ResourceType r : ResourceType.values()) {
                fw.write(r.name() + "," + state.getResourceAmount(r) + "\n");
            }

            fw.write("[PRODUCERS]\n");
            for (Producer p : state.getProducers()) {
                fw.write(p.toCSV() + "\n");
            }

            fw.write("[CONVERTERS]\n");
            for (Converter c : state.getConverters()) {
                fw.write(c.toCSV() + "\n");
            }

            fw.write("[CONSUMERS]\n");
            for (Consumer c : state.getConsumers()) {
                fw.write(c.toCSV() + "\n");
            }

        } catch (IOException e) {
            return "Save failed: " + e.getMessage();
        }

        return "Saved to " + filename;
    }

   
    public String load(String filename) {
        File file = new File(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            state.clearAll();
            currentTick = 0;

            String line;
            boolean inResources = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("CurrentTick")) {
                    // Tick count is ignored (reset to 0)
                    continue;
                }

                if (line.startsWith("[")) {
                    // Detecting resource section
                    inResources = "[RESOURCES]".equals(line);
                    continue;
                }

                if (inResources) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        try {
                            ResourceType type = ResourceType.valueOf(parts[0]);
                            int amount = Integer.parseInt(parts[1]);
                            state.updateResource(type, amount);
                        } catch (IllegalArgumentException e) {
                            // Ignore unknown resource names
                        }
                    }
                }
            }
        } catch (IOException e) {
            return "Load failed: " + e.getMessage();
        }

        return "Loaded from " + filename;
    }

    
    public void initialiseDefaults() {
        state.getProducers().clear();
        state.getConverters().clear();
        state.getConsumers().clear();

        // Producer: Mine → ORE
        Producer mine = new Producer("Mine", ResourceType.ORE, 3) {
            @Override
            public void produce(Context ctx) {
                ctx.getState().addResource(product, amount);
            }

            @Override
            public String toCSV() {
                return "producer," + name + "," + product + "," + amount;
            }
        };

        // Producer: Printer → METAL
        Producer printer = new Producer("Printer", ResourceType.METAL, 1) {
            
            public void produce(Context ctx) {
                ctx.getState().addResource(product, amount);
            }

         
            public String toCSV() {
                return "producer," + name + "," + product + "," + amount;
            }
        };

        // Converter: ORE → METAL
        Converter refinery = new Converter(
                "Refinery",
                ResourceType.ORE, 2,
                ResourceType.METAL, 1) {

          
            public void convert(Context ctx) {
                boolean ok = ctx.getState().removeResource(input, inputAmount);
                if (ok) {
                    ctx.getState().addResource(output, outputAmount);
                }
            }

            @Override
            public String toCSV() {
                return "converter," + name + "," + input + "," + inputAmount
                        + "," + output + "," + outputAmount;
            }
        };

        // Consumer: Port consumes METAL
        Consumer port = new Consumer("Port", ResourceType.METAL, 1) {
            @Override
            public void consume(Context ctx) {
                ctx.getState().removeResource(product, amount);
            }

            @Override
            public String toCSV() {
                return "consumer," + name + "," + product + "," + amount;
            }
        };

        state.addProducer(mine);
        state.addProducer(printer);
        state.addConverter(refinery);
        state.addConsumer(port);
    }
}
