package LoginAdmin.dao;

import LoginAdmin.resource.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRegisterDAO extends AbstractDAO{

    private static final String STATEMENET_REGISTRATION = "INSERT INTO admin(id, email, password) values (?, ?, ?)";
    private final Admin admin;

    public AdminRegisterDAO(final Connection con, final Admin admin) {
        super(con);
        this.admin = admin;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs1 = null;
        // the results of the search

        try {
            stmnt = con.prepareStatement(STATEMENET_REGISTRATION);
            stmnt.setString(1, student.getEmail());
            stmnt.setString(2, student.getPassword());

            stmnt.execute();
            LOGGER.info("Admin registered {}.", admin.getEmail());


        } finally {

            if (stmnt != null) {
                stmnt.close();
            }


        }
    }



}
