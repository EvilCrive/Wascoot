package wascoot.database;

import wascoot.resource.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class getModelListDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT name, brand, battery_life, weight, height, length, depth, rate_per_min, rate_per_model FROM public.model";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for listing all the models.
     *
     * @param con
     *            the connection to the database.
     */
    public getModelListDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Lists all the models in the database.
     *
     * @return a list of {@code model} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for employees.
     */
    public List<Model> getModelList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Model> models = new ArrayList<Model>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                models.add(new Model(rs.getString("name"), rs
                        .getString("brand"), rs.getString("battery_life"),
                        rs.getInt("weight"), rs.getInt("height"),rs.getInt("length"),
                        rs.getInt("depth"), rs.getInt("rate_per_min"),rs.getInt("rate_per_model")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return models;
    }
}
