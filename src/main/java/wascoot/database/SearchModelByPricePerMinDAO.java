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

import wascoot.resource.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public final class SearchModelByPricePerMinDAO extends AbstractDAO<List<Model>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT name, brand, battery_life, price_per_min FROM public.Model WHERE price_per_min > ?";


	private final double pricePerMin;


	public SearchModelByPricePerMinDAO(final Connection con, final double pricePerMin) {
		super(con);
		this.pricePerMin = pricePerMin;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Model> models = new ArrayList<Model>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setDouble(1, pricePerMin);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				models.add(new Model(rs.getString("name"), rs.getString("brand"), Time.valueOf(rs.getString("battery_life")),
						rs.getDouble("price_per_min")));
			}

			LOGGER.info("Model(s) with price above %d successfully listed.", pricePerMin);
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = models;
	}
}
