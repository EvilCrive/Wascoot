package wascoot.database;

import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ScooterRackDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT id, total_parking_spots, available_parking_spots, postalcode, address FROM public.scooterracks";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for listing all the scooterracks.
     *
     * @param con
     *            the connection to the database.
     */
    public ScooterRackDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Lists all the scooter racks in the database.
     *
     * @return a list of {@code scooterrack} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for scooterracks.
     */
    public List<Scooterrack> getScooterRackList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Scooterrack> scooterracks = new ArrayList<Scooterrack>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                scooterracks.add(new Scooterrack(rs.getInt("id"), rs
                        .getInt("total_parking_spots"), rs.getInt("available_parking_spots"),
                        rs.getString("postalcode"), rs.getString("address") ));
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

        return scooterracks;
    }
}
