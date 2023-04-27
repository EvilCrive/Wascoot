package wascoot.database;


import wascoot.resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UpdatePaymentMethodDAO extends AbstractDAO{

    private static final String STATEMENT = "UPDATE public.PaymentMethod SET Activation=? WHERE type=? RETURNING *";

    private final PaymentMethod paymentMethod;

    public UpdatePaymentMethodDAO(final Connection con, final PaymentMethod paymentMethod) {
        super(con);

        if (paymentMethod == null) {
            LOGGER.error("The payment method cannot be null.");
            throw new NullPointerException("The payment method cannot be null.");
        }

        this.paymentMethod = paymentMethod;
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1,paymentMethod.getActivation());
            pstmt.setString(2, paymentMethod.getType());

            pstmt.execute();

            LOGGER.info("payment method %s successfully stored in the database.", paymentMethod.getType());
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
