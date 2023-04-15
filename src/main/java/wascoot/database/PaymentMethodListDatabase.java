package wascoot.database;


import wascoot.resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodListDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT id, type, activation FROM public.PaymentMethod";

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
    public PaymentMethodListDatabase(final Connection con) {
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
    public List<PaymentMethod> getPaymentMethodList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                paymentMethods.add(new PaymentMethod(rs.getInt("id"), rs.getString("type"), rs.getString("activation")));
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

        return paymentMethods;
    }
}
