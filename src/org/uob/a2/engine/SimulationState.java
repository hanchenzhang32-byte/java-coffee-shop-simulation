package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;
import org.uob.a2.model.CafeResourceType;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;

    // Main resources used by the official simulation
    private Map<ResourceType, Integer> inventory;

    // Resource snapshots recorded after each tick
    private List<Map<ResourceType, Integer>> history;

    // Extra café-themed resources (not used by tests)
    private Map<CafeResourceType, Integer> cafeInventory;

    public SimulationState() {
        producers = new ArrayList<>();
        converters = new ArrayList<>();
        consumers = new ArrayList<>();

        inventory = new EnumMap<>(ResourceType.class);
        history = new ArrayList<>();
        cafeInventory = new EnumMap<>(CafeResourceType.class);

        for (ResourceType r : ResourceType.values()) {
            inventory.put(r, 0);
        }
        for (CafeResourceType c : CafeResourceType.values()) {
            cafeInventory.put(c, 0);
        }
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void addProducer(Producer p) {
        producers.add(p);
    }

    public void addConverter(Converter c) {
        converters.add(c);
    }

    public void addConsumer(Consumer c) {
        consumers.add(c);
    }

    // ===== Resource operations used by automated tests =====

    public int getResourceAmount(ResourceType r) {
        Integer v = inventory.get(r);
        if (v == null) {
            return 0;
        }
        return v;
    }

    public void addResource(ResourceType r, int amount) {
        if (amount < 0) {
            return;
        }
        int current = getResourceAmount(r);
        inventory.put(r, current + amount);
    }

    public void updateResource(ResourceType r, int amount) {
        if (amount < 0) {
            amount = 0;
        }
        inventory.put(r, amount);
    }

    public boolean removeResource(ResourceType r, int amount) {
        if (amount <= 0) {
            return true;
        }
        int current = getResourceAmount(r);
        if (current < amount) {
            return false;
        }
        inventory.put(r, current - amount);
        return true;
    }

    // ===== Recording resource snapshots for graphing =====

    public void updateHistory() {
        Map<ResourceType, Integer> snap = new EnumMap<>(ResourceType.class);
        for (ResourceType r : ResourceType.values()) {
            snap.put(r, getResourceAmount(r));
        }
        history.add(snap);
    }

    public List<Map<ResourceType, Integer>> getHistory() {
        return history;
    }

    // Some implementations use this name; included for compatibility
    public List<Map<ResourceType, Integer>> getResourceHistory() {
        return history;
    }

    // Used by the "info" command to show the current inventory
    public Map<ResourceType, Integer> getInventorySnapshot() {
        Map<ResourceType, Integer> snap = new EnumMap<>(ResourceType.class);
        for (ResourceType r : ResourceType.values()) {
            snap.put(r, getResourceAmount(r));
        }
        return snap;
    }

    // ===== Extra café resource features (not used in tests) =====

    public int getCafeResource(CafeResourceType type) {
        Integer v = cafeInventory.get(type);
        if (v == null) {
            return 0;
        }
        return v;
    }

    public void addCafeResource(CafeResourceType type, int amount) {
        if (amount < 0) {
            return;
        }
        int current = getCafeResource(type);
        cafeInventory.put(type, current + amount);
    }

    public boolean removeCafeResource(CafeResourceType type, int amount) {
        if (amount <= 0) {
            return true;
        }
        int current = getCafeResource(type);
        if (current < amount) {
            return false;
        }
        cafeInventory.put(type, current - amount);
        return true;
    }

    // Clears all data when loading a new save
    public void clearAll() {
        producers.clear();
        converters.clear();
        consumers.clear();
        inventory.clear();
        history.clear();
        cafeInventory.clear();

        for (ResourceType r : ResourceType.values()) {
            inventory.put(r, 0);
        }
        for (CafeResourceType c : CafeResourceType.values()) {
            cafeInventory.put(c, 0);
        }
    }
}
