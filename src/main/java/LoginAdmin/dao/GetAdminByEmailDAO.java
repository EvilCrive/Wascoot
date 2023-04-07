package LoginAdmin.dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import LoginAdmin.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAdminByEmailDAO extends AbstractDAO<Boolean> {
    private static final String STATEMENT_ADMIN_INFO = "SELECT id,email,password from admin where email = ?;";


    private final String email;

    public GetAdminByEmailDAO(final Connection con, final Administrator admin) {
        super(con);
        this.email = admin.getEmail();
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        boolean found = false;
        // the results of the search

        try {
            stmnt = con.prepareStatement(STATEMENT_ADMIN_INFO);
            stmnt.setString(1, email);

            rs = stmnt.executeQuery();

            if(rs.next()){
                found = true;
                LOGGER.info("Admin found {}, {}.", email);

            }else{
                LOGGER.info("Admin NOT found {}, {}.", email);

            }



        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            con.close();
        }
        this.outputParam = found;
    }


}
