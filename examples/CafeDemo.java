import org.uob.a2.engine.*;
import org.uob.a2.model.*;

/** Portfolio demonstration of the original cafe model classes. */
public class CafeDemo {
    public static void main(String[] args) {
        SimulationState state = new SimulationState();
        Context context = new Context(new Engine(state), state);
        BaseCafeEntity[] entities = {new BeanGrinder(), new CoffeeWaterPump(),
            new MilkSupplier(), new EspressoMachine(), new LatteMachine(), new CustomerCounter()};
        for (int tick = 1; tick <= 5; tick++) {
            for (BaseCafeEntity entity : entities) entity.tick(context);
            System.out.printf("Tick %d: beans=%d, customers=%d%n", tick,
                state.getCafeResource(CafeResourceType.CAFE_BEANS),
                state.getCafeResource(CafeResourceType.CUSTOMERS));
        }
    }
}
