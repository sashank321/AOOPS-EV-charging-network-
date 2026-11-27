import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ChargingStation {
    private final String name;
    private final Set<Charger> chargers = new LinkedHashSet<>();

    public ChargingStation(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Station name is required.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addCharger(Charger charger) {
        return chargers.add(Objects.requireNonNull(charger, "Charger is required."));
    }

    public List<Charger> getChargers() {
        return List.copyOf(chargers);
    }

    public List<Charger> findAvailableChargers(Charger.Type type) {
        List<Charger> available = new ArrayList<>();
        for (Charger charger : chargers) {
            if (charger.getType() == type && charger.getStatus() == Charger.Status.AVAILABLE) {
                available.add(charger);
            }
        }
        return List.copyOf(available);
    }
}
