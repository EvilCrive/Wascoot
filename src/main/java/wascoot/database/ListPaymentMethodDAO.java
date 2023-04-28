package wascoot.database;

import wascoot.resource.Model;
import wascoot.resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public final class ListPaymentMethodDAO extends AbstractDAO<List<PaymentMethod>> {

    private static final String STATEMENT = "SELECT ID, type, Activation FROM public.paymentmethod";

    public ListPaymentMethodDAO(final Connection con){
        super(con);
    }

    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                paymentMethods.add(new PaymentMethod(rs.getInt("ID"),
                        rs.getString("type"), rs.getString("Activation")));
            }

            LOGGER.info("payment method(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = paymentMethods;
    }
}
