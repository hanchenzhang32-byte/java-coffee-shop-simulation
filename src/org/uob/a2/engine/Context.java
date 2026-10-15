package org.uob.a2.engine;

public class Context {

    private final Engine engine;
    private final SimulationState state;

    public Context(Engine engine, SimulationState state) {
        this.engine = engine;
        this.state = state;
    }

    
    public Engine getEngine() {
        return engine;
    }

    public SimulationState getState() {
        return state;
    }

    public Engine engine() {
        return engine;
    }

    public SimulationState state() {
        return state;
    }
}
