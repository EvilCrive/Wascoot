
package wascoot.resource;

public final class Actions {

	/**
	 * The creation of an Administrator
	 */
	public static final String CREATE_ADMINISTRATOR = "CREATE_ADMINISTRATOR";


	public static final String LOAD_ADMINISTRATOR_PHOTO = "LOAD_ADMINISTRATOR_PHOTO";

	/**
	 * The search of Administrators by their salary
	 */
	public static final String SEARCH_ADMINISTRATOR_BY_ID = "SEARCH_ADMINISTRATOR_BY_ID";

	public static final String SEARCH_ADMINISTRATOR_BY_EMAIL = "SEARCH_ADMINISTRATOR_BY_EMAIL";

	/**
	 * This class can be neither instantiated nor sub-classed.
	 */
	private Actions() {
		throw new AssertionError(String.format("No instances of %s allowed.", Actions.class.getName()));
	}
}
