package wascoot.database;

import wascoot.resource.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListCustomerDAO extends AbstractDAO<List<Customer>> {

    private static final String STATEMENT = "SELECT cf, name, surname, email, sex, birthdate, postalCode, paymentType FROM public.Customer";

    public ListCustomerDAO(final Connection con){
        super(con);
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Customer> customers = new ArrayList<Customer>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("sex"), rs.getString("birthdate"),rs.getString("postalCode"),
                        rs.getString("paymentType")));
            }

            LOGGER.info("Customer(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = customers;
    }
}
