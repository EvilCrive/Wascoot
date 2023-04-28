/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public final class UpdateAdministratorDAO extends AbstractDAO<Administrator> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String UPDATE_ADMINISTRATOR = "UPDATE public.admin SET id = ?, email = ?, password = ? WHERE email = ? RETURNING *";

	/**
	 * The administrator to be updated in the database
	 */
	private final Administrator administrator;

	/**
	 * Creates a new object for updat an administrator.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param administrator
	 *            the administrator to be updated in the database.
	 */
	public UpdateAdministratorDAO(final Connection con, final Administrator administrator) {
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
		ResultSet rs = null;

		// the updated administrator
		Administrator e = null;

		try {
			pstmt = con.prepareStatement(UPDATE_ADMINISTRATOR);
			pstmt.setInt(1, administrator.getId());
			pstmt.setString(2, administrator.getEmail());
			pstmt.setString(3, administrator.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				e = new Administrator(rs.getInt("id"), rs.getString("email"), rs.getString("password"));

				LOGGER.info("Administrator %d successfully updated in the database.", e.getId());
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
		}

		outputParam = e;
	}
}
