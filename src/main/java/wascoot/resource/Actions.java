package wascoot.resource;

public class Actions {
    public static final String INSERT_NEW_MODEL = "INSERT_NEW_MODEL";
    public static final String LIST_SCOOTERRACKS = "LIST_SCOOTERRACKS";
    public static final String LIST_SCOOTERS = "LIST_SCOOTERS";
    public static final String UPDATE_SCOOTERRACK = "UPDATE_SCOOTERRACK";
    public static final String INSERT_NEW_SCOOTERRACK = "INSERT_NEW_SCOOTERRACK";
    public static final String INSERT_NEW_SCOOTER = "INSERT_NEW_SCOOTER";

    public static final String CREATE_ADMINISTRATOR = "CREATE_ADMINISTRATOR";
    public static final String LOAD_ADMINISTRATOR_PHOTO = "LOAD_ADMINISTRATOR_PHOTO";
    public static final String SEARCH_ADMINISTRATOR_BY_ID = "SEARCH_ADMINISTRATOR_BY_ID";
    public static final String SEARCH_ADMINISTRATOR_BY_EMAIL = "SEARCH_ADMINISTRATOR_BY_EMAIL";

    public static final String GET_ALL_CUSTOMERS= "GET_ALL_CUSTOMERS";


    public static final String READ_ADMINISTRATOR = "READ_ADMINISTRATOR";

    public static final String UPDATE_ADMINISTRATOR = "UPDATE_ADMINISTRATOR";
    public static final String DELETE_ADMINISTRATOR = "DELETE_ADMINISTRATOR";
    public static final String LIST_ADMINISTRATOR = "LIST_ADMINISTRATOR";

    private Actions() {
        throw new AssertionError(String.format("No instances of %s allowed.", Actions.class.getName()));
    }
}
