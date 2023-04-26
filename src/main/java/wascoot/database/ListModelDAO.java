package wascoot.database;

import wascoot.resource.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ListModelDAO extends AbstractDAO<List<Model>> {

    private static final String STATEMENT = "SELECT name, brand, battery_life, price_per_min FROM public.model";

    public ListModelDAO(final Connection con){
        super(con);
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Model> models = new ArrayList<Model>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                models.add(new Model(rs.getString("name"), rs.getString("brand"), rs.getString("battery_life"),
                        rs.getDouble("price_per_min")));
            }

            LOGGER.info("Model(s) successfully listed.");
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