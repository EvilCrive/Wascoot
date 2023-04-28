package wascoot.database;

import wascoot.resource.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class InsertNewModelDAO extends AbstractDAO<Model>{
    private static final String STATEMENT = "INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES (?, ?, ?, ?) RETURNING *";


    private final Model model;

    public InsertNewModelDAO(final Connection con, final Model model) {
        super(con);

        if (model == null) {
            throw new NullPointerException("The employee cannot be null.");
        }

        this.model = model;
    }

    @Override
    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the created model
        Model e = null;

        try {
            //name, brand, battery_life, price_per_min
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, model.getName());
            pstmt.setString(2, model.getBrand());
            pstmt.setTime(3, model.getBatteryLife());
            pstmt.setDouble(4, model.getPricePerMin());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                e = new Model(rs.getString("name"), rs
                        .getString("brand"), Time.valueOf(rs.getString("battery_life")),
                        rs.getDouble("price_per_min"));

                LOGGER.info("Model %d successfully stored in the database.", e.getName());
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

        outputParam = e;

    }
}
