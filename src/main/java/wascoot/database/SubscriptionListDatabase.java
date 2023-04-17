package wascoot.database;

import wascoot.resource.Customer;
import wascoot.resource.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionListDatabase {
    private static final String STATEMENT = "SELECT id, type, daily_unlocks, validity_per_day, fixed_price FROM public.Subscription";

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
    public SubscriptionListDatabase(final Connection con) {
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
    public List<Subscription> getSubscriptionList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Subscription> subscriptions = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                subscriptions.add(new Subscription(rs.getInt("id"), rs.getString("type"), rs.getInt("daily_unlocks"),
                        rs.getString("validity_per_day"), rs.getDouble("fixed_price")));
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

        return subscriptions;
    }
}
