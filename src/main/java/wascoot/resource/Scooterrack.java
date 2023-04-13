package wascoot.resource;

public class Scooterrack {
    /**
     * The identifier of the scooter rack (PK)
     */
    private final int id;

    /**
     * The total parking spots in a scooter rack
     */
    private final int totalParkingSpots;

    /**
     * The available parking spots in a scooter rack
     */
    private final int availableParkingSpots;

    /**
     * The postal code of a scooter rack
     */
    private final String postalCode;

    /**
     * The address of the scooter rack
     */
    private final String address;



    /**
     * Creates a new scooter rack
     *
     * @param id
     *            the id of a scooter rack.
     * @param totalParkingSpots
     *            the total parking spots of a scooter rack.
     * @param availableParkingSpots
     *            the total available parking spots of one scooter rack.
     * @param postalCode
     *            the postal code of a scooter rack.
     * @param address
     *            the address of a scooter rack.
     */
    public Scooterrack(final int id, final int totalParkingSpots, final int availableParkingSpots, final String postalCode, final String address ) {
        this.id = id;
        this.totalParkingSpots = totalParkingSpots;
        this.availableParkingSpots = availableParkingSpots;
        this.postalCode = postalCode;
        this.address = address;
    }

    /**
     * Returns the id of the scooter rack.
     *
     * @return the id of scooter rack.
     */
    public final int getId() {
        return id;
    }

    /**
     * Returns the total parking spots.
     *
     * @return the total parking spots.
     */
    public final int getTotalParkingSpots() {
        return totalParkingSpots;
    }

    /**
     * Returns the available parking spots of a scooter rack.
     *
     * @return the available parking spots of a scooter rack.
     */
    public final int getAvailableParkingSpots() {
        return availableParkingSpots;
    }

    /**
     * Returns the postal code of a scooter rack.
     *
     * @return the postal code of a scooter rack.
     */
    public final String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the address of a scooter rack.
     *
     * @return the address of a scooter rack.
     */
    public final String getAddress() {
        return address;
    }
}
