package wascoot.database;

import wascoot.resource.Subscription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ListSubscriptionDAO extends AbstractDAO<List<Subscription>> {

    private static final String STATEMENT = "SELECT id, type, daily_unlocks, validity_per_day, fixed_price FROM public.Subscription";

    public ListSubscriptionDAO(final Connection con){
        super(con);
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Subscription> subscriptions = new ArrayList<Subscription>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                subscriptions.add(new Subscription(rs.getInt("id"), rs.getString("type"), rs.getInt("daily_unlocks"),
                        Time.valueOf(rs.getString("validity_per_day")), rs.getDouble("fixed_price")));
            }

            LOGGER.info("Subscription(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = subscriptions;
    }
}