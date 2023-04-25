package wascoot.database;

import wascoot.resource.Scooter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScooterDatabase {
     import java.util.ArrayList;
 import java.util.List;

public final class ScooterDatabase {
public final class ScooterDatabase extends AbstractDAO<Scooter> {
            /**
             * The SQL statement to be executed
             */
            /**
             * The connection to the database
             */
    private final Connection con;


            /**
             * Creates a new object for listing all the scooter.
             *            the connection to the database.
             */
            public ScooterDatabase(final Connection con) {
                        this.con = con;
                        super(con);
                    }

            @Override
    protected void doAccess() throws Exception {

                        PreparedStatement pstmt = null;
                        ResultSet rs = null;

                               // the results of the search
                                       final List<Scooter> scooter = new ArrayList<Scooter>();

                                try {
                                pstmt = con.prepareStatement(STATEMENT);

                                       rs = pstmt.executeQuery();
                    //id, date_of_purchase, km_traveled, model, remaining_battery_life
                                        while (rs.next()) {
                                        scooter.add(new Scooter(rs.getInt("id"), rs.getInt("date_of_purchase"), rs.getInt("km_traveled"),
                                                        rs.getString("model"), rs.getString("remaining_battery_life")));
                                    }

                                        LOGGER.info("Scooter(s) successfully listed.");
                            } finally {
                                if (rs != null) {
                                        rs.close();
                                    }

                                        if (pstmt != null) {
                                       pstmt.close();
                                    }
                    +
                                    }
                +
                                outputParam = scooter;
            }

/**
}
