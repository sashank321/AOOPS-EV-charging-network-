public class ChargingStationNetwork {
    public static void main(String[] args) {
        ChargingStation station = new ChargingStation("Campus Charging Station");
        station.addCharger(new Charger("AC-01", Charger.Type.AC, true));
        Charger fastCharger = new Charger("DC-01", Charger.Type.DC_FAST, true);
        station.addCharger(fastCharger);
        station.addCharger(new Charger("DC-02", Charger.Type.DC_FAST, false));

        System.out.println(station.getName());
        for (Charger charger : station.getChargers()) {
            System.out.println(charger.getId() + " | " + charger.getType() + " | " + charger.getStatus());
        }

        System.out.println("\nAvailable DC fast chargers:");
        for (Charger charger : station.findAvailableChargers(Charger.Type.DC_FAST)) {
            System.out.println(charger.getId());
        }

        System.out.println("\nReservation demo:");
        System.out.println("Asha reserves DC-01: " + fastCharger.reserve("Asha"));
        System.out.println("Ravi reserves DC-01: " + fastCharger.reserve("Ravi"));
        System.out.println("Available DC fast chargers: "
                + station.findAvailableChargers(Charger.Type.DC_FAST).size());
        System.out.println("Ravi cancels: " + fastCharger.cancelReservation("Ravi"));
        System.out.println("Asha cancels: " + fastCharger.cancelReservation("Asha"));
        System.out.println("DC-01 after cancellation: " + fastCharger.getStatus());
    }
}
