
package wascoot.resource;

public class Administrator {

	/**
	 * The badge number (identifier) of the Administrator
	 */
	public final int id;

	/**
	 * The surname of the Administrator
	 */
	public final String email;

	/**
	 * The age of the Administrator
	 */
	public final String password;

	public final byte[] photo;

	/**
	 * The MIME media type of photo of the employee
	 */
	public final String photoMediaType;

	/**
	 * The salary of the Administrator
	 */

	/**
	 * Creates a new employee
	 * 
	 * @param id
	 *            the id of the Administrator
	 * @param email
	 *            the email of the Administrator.
	 * @param password
	 *            the password of the Administrator.
	 */
	public Administrator(final int id, final String email, final String password, final byte[] photo, final String photoMediaType) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.photoMediaType = photoMediaType;
	}


	/**
	 * Returns the id of the Administrator.
	 * 
	 * @return the id of the Administrator.
	 */
	public final int getId() { return id; }

	/**
	 * Returns the Email of the Administrator.
	 * 
	 * @return the Email of the Administrator.
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Returns the password of the Administrator.
	 * 
	 * @return the password of the Administrator.
	 */
	public final String getPassword() {
		return password;
	}

	public final byte[] getPhoto() {
		return photo;
	}
	public final String getPhotoMediaType() {
		return photoMediaType;
	}
	public final boolean hasPhoto() {
		return photo != null && photo.length > 0 && photoMediaType != null && !photoMediaType.isBlank();
	}
	public final int getPhotoSize() {
		return photo != null ? photo.length : Integer.MIN_VALUE;
	}

}
