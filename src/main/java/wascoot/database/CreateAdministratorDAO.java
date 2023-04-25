

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class CreateAdministratorDAO extends AbstractDAO {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO public.admin (id, email, password, photo, photoMediaType) VALUES (?, ?, ?, ?, ?)";

	/**
	/**
	 * The administrator to be stored into the database
	 */
	private final Administrator administrator;

	/**
	 * Creates a new object for storing an administrator into the database.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param administrator
	 *            the administrator to be stored into the database.
	 */
	public CreateAdministratorDAO(final Connection con, final Administrator administrator) {
		super(con);

		if (administrator == null) {
			LOGGER.error("The administrator cannot be null.");
			throw new NullPointerException("The administrator cannot be null.");
		}

		this.administrator = administrator;
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, administrator.getId());
			pstmt.setString(2, administrator.getEmail());
			pstmt.setString(3, administrator.getPassword());
			pstmt.setBytes(4, administrator.getPhoto());
			pstmt.setString(5, administrator.getPhotoMediaType());

			pstmt.execute();

			LOGGER.info("Administrator %d successfully stored in the database.", administrator.getId());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}
}
