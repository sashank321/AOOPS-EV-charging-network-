import java.util.Objects;

public class Charger {
    public enum Type {
        AC, DC_FAST
    }

    public enum Status {
        AVAILABLE, RESERVED, OFFLINE
    }

    private final String id;
    private final Type type;
    private final boolean online;
    private String reservedBy;

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

    public synchronized Status getStatus() {
        if (!online) {
            return Status.OFFLINE;
        }
        return reservedBy == null ? Status.AVAILABLE : Status.RESERVED;
    }

    public synchronized boolean reserve(String driverId) {
        if (driverId == null || driverId.isBlank()) {
            throw new IllegalArgumentException("Driver ID is required.");
        }
        if (!online || reservedBy != null) {
            return false;
        }
        reservedBy = driverId;
        return true;
    }

    public synchronized boolean cancelReservation(String driverId) {
        if (reservedBy == null || !reservedBy.equals(driverId)) {
            return false;
        }
        reservedBy = null;
        return true;
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
