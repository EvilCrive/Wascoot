package wascoot.rest;

import wascoot.resource.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

public class Modeldatabasejava import java.util.ArrayList;
        import java.util.List;

        -public final class ModelDatabase {
+public final class ModelDatabase {
        /**
         * The SQL statement to be executed
         */
        /**
         * The connection to the database
         */
-
        public final Connection con;
+

        /**
         * Creates a new object for listing all the Models.
         *            the connection to the database.
         */
        public ModelDatabase(final Connection con) {
            -        this.con = con;
            +        super(con);
            +    }
+
        +    @Override
+    protected void doAccess() throws Exception {
            +
                    +PreparedStatement pstmt = null;
            +ResultSet rs = null;
            +
                    +        // the results of the search
                            +        final List<Model> Model = new ArrayList<Model>();
            +
                    +        try {
                +            pstmt = con.prepareStatement(STATEMENT);
                +
                        +            rs = pstmt.executeQuery();
                +//name, brand, battery-life, price-per-min
                        +            while (rs.next()) {
                    +                Model.add(new Model(rs.getInt("name"), rs.getInt("brand"), rs.getInt("battery-life"),
                            +                        rs.getString("price-per-min")));
                    +            }
                +
                        +            LOGGER.info("Model(s) successfully listed.");
                +        } finally {
                +            if (rs != null) {
                    +                rs.close();
                    +            }
                +
                        +            if (pstmt != null) {
                    +                pstmt.close();
                    +            }
                +
                        +        }
            +
                    +        outputParam = Model;
        }

/**{
}
