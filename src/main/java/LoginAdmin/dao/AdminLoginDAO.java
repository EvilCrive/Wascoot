package LoginAdmin.dao;

import LoginAdmin.resource.Administrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAO extends AbstractDAO<Administrator>  {

    private static final String STATEMENT_LOGIN = "SELECT id,email,password FROM public.admin WHERE id=? AND email=? AND password=?;";

    private final Administrator admin;

    public AdminLoginDAO(final Connection con, final Administrator admin) {
        super(con);
        this.admin = admin;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        // the results of the search
        Administrator admin_new = null;
        try {
            stmnt = con.prepareStatement(STATEMENT_LOGIN);
            stmnt.setString(1, admin.getId());
            stmnt.setString(2, admin.getEmail());
            stmnt.setString(3, admin.getPassword());
            //stmnt.setLong(3, admin.getProfleImage());

            rs = stmnt.executeQuery();


            if(rs.next()){

                admin_new = new Administrator(rs.getString("id"), rs.getString("email"), rs.getString("password"));
                LOGGER.info("Admin logged in {}.", admin_new.getEmail());

            }else{
                LOGGER.error("error logging in the admin {}",admin.getEmail());

            }

//            return null;



        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }
        this.outputParam = admin_new;
    }




}
