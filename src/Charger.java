import java.util.Objects;

public class Charger {
    public enum Type {
        AC, DC_FAST
    }

    public enum Status {
        AVAILABLE, OFFLINE
    }

    private final String id;
    private final Type type;
    private final boolean online;

    public Charger(String id, Type type, boolean online) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Charger ID is required.");
        }
        this.id = id;
        this.type = Objects.requireNonNull(type, "Charger type is required.");
        this.online = online;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return online ? Status.AVAILABLE : Status.OFFLINE;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Charger)) {
            return false;
        }
        Charger charger = (Charger) other;
        return id.equals(charger.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
