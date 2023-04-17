package wascoot.database;

import wascoot.resource.Revenue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentWithSubscriptionDatabase {

    private static final String STATEMENT = "select production_to_month , sum(price) pr from ( SELECT DATE_TRUNC('month', withS.date_hour) AS production_to_month, SUM(s.fixed_price) AS price FROM paymentwithsubscription withS join usedsubscription u on withS.usedsubscription_id = u.id join subscription s on u.subscription_type = s.id GROUP BY DATE_TRUNC('month',withS.date_hour) union SELECT DATE_TRUNC('month', withoutS.date_hour) AS production_to_month, SUM(withoutS.price) AS price FROM paymentwithoutsubscription withoutS GROUP BY DATE_TRUNC('month',withoutS.date_hour) ) x group by production_to_month order by production_to_month;";

    private final Connection con;

    public PaymentWithSubscriptionDatabase(Connection con) {
        this.con = con;
    }


    public List<Revenue> getRevenueInfo() throws SQLException {



        PreparedStatement pstmt = null;
        ResultSet rs = null;


        final List<Revenue> revenues = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                revenues.add(new Revenue(rs.getString("production_to_month"), rs.getInt("pr")));
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

        return revenues;
    }

}
