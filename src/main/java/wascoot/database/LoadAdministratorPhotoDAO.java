
package wascoot.database;

import wascoot.resource.Administrator;

import javax.naming.directory.NoSuchAttributeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;


public final class LoadAdministratorPhotoDAO extends AbstractDAO<Administrator> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM public.admin  WHERE id = ?";

	/**
	 * The id of the Administrator
	 */
	private final int id;

	public LoadAdministratorPhotoDAO(final Connection con, final int id) {
		super(con);
		this.id = id;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		Administrator e = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				e = new Administrator(Integer.MIN_VALUE, null, null,
						rs.getBytes("photo"), rs.getString("photoMediaType"));

				LOGGER.info("Photo for administrator %d successfully loaded.", id);
			} else {
				LOGGER.warn("Administrator %d not found.", id);
				throw new SQLException(String.format("Administrator %d not found.", id), "NOT_FOUND");
			}


		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = e;
	}
}
