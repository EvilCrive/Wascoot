package LoginAdmin.dao;

import LoginAdmin.resource.Administrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRegisterDAO extends AbstractDAO{

    private static final String STATEMENET_REGISTRATION = "INSERT INTO public.admin VALUES(id, email, password, profile_image) values (?, ?, ?, NULL);";
    private final Administrator admin;

    public AdminRegisterDAO(final Connection con, final Administrator admin) {
        super(con);

        if (admin == null) {
            LOGGER.error("The admin cannot be null.");
            throw new NullPointerException("The admin cannot be null.");
        }

        this.admin = admin;
    }

    @Override
    protected final void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        // the results of the search

        try {
            stmnt = con.prepareStatement(STATEMENET_REGISTRATION);
            stmnt.setString(1, admin.getId());
            stmnt.setString(2, admin.getEmail());
            stmnt.setString(3, admin.getPassword());

            stmnt.execute();
            LOGGER.info("Admin registered {}.", admin.getId());


        } finally {

            if (stmnt != null) {
                stmnt.close();
            }


        }
    }



}
