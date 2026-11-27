public class ChargingStationNetwork {
    public static void main(String[] args) {
        ChargingStation station = new ChargingStation("Campus Charging Station");
        station.addCharger(new Charger("AC-01", Charger.Type.AC, true));
        station.addCharger(new Charger("DC-01", Charger.Type.DC_FAST, true));
        station.addCharger(new Charger("DC-02", Charger.Type.DC_FAST, false));

        System.out.println(station.getName());
        for (Charger charger : station.getChargers()) {
            System.out.println(charger.getId() + " | " + charger.getType() + " | " + charger.getStatus());
        }

        System.out.println("\nAvailable DC fast chargers:");
        for (Charger charger : station.findAvailableChargers(Charger.Type.DC_FAST)) {
            System.out.println(charger.getId());
        }
    }
}
