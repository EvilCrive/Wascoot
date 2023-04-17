

package wascoot.database;

import wascoot.resource.Administrator;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SearchAdministratorByEmailDAO extends AbstractDAO<List<Administrator>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT *FROM public.admin WHERE email= ?";

	/**
	 * The email of the administrator
	 */
	private final String email;


	public SearchAdministratorByEmailDAO(final Connection con, final String email) {
		super(con);
		this.email = email;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Administrator> administrator = new ArrayList<Administrator>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrator.add(new Administrator(rs.getInt("id"), rs.getString("email"),
						rs.getString("password"), null, null));
			}

			LOGGER.info("Administrator successfully returned.");
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = administrator;
	}
}
