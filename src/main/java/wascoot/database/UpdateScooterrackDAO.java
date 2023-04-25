package wascoot.database;

import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UpdateScooterrackDAO extends AbstractDAO<Scooterrack>{

    private static final String STATEMENT = "UPDATE public.scooterracks SET id=?, total_parking_spots=?, available_parking_spots=?, postalcode=?, address=? WHERE id=? RETURNING *";

    private final Scooterrack scooterrack;

    public UpdateScooterrackDAO(final Connection con, final Scooterrack s) {
        super(con);

        if (s == null) {
            LOGGER.error("The scooterrack cannot be null.");
            throw new NullPointerException("The scooterrack cannot be null.");
        }

        this.scooterrack = s;
    }

    protected void doAccess() throws SQLException{

        PreparedStatement pstmt = null;


        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setInt(1, scooterrack.getId());
            pstmt.setInt(2, scooterrack.getTotalParkingSpots());
            pstmt.setInt(3, scooterrack.getAvailableParkingSpots());
            pstmt.setString(4, scooterrack.getPostalCode());
            pstmt.setString(5, scooterrack.getAddress());
            pstmt.setInt(6, scooterrack.getId());

            Boolean result = pstmt.execute();
            if (result) {
                LOGGER.info("Scooterrack %s successfully stored in the database.", scooterrack.getId());
            }
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
