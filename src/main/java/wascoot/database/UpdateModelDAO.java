package wascoot.database;

import wascoot.resource.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UpdateModelDAO extends AbstractDAO{

    private static final String STATEMENT = "UPDATE public.Model SET brand=?, battery_life=?, price_per_Min=? WHERE name=? RETURNING *";

    private final Model model;

    public UpdateModelDAO(final Connection con, final Model model) {
        super(con);

        if (model == null) {
            LOGGER.error("The model cannot be null.");
            throw new NullPointerException("The model cannot be null.");
        }

        this.model = model;
    }

    protected final void doAccess() throws SQLException{

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1,model.getBrand());
            pstmt.setTime(2, model.getBatteryLife());
            pstmt.setDouble(3, model.getPricePerMin());
            pstmt.setString(4,model.getName());

            pstmt.execute();

            LOGGER.info("Model %s successfully stored in the database.", model.getName());
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
