package it.unipd.dei.webapp.LoginRegister.dao;

import it.unipd.dei.webapp.LoginRegister.resource.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAO extends AbstractDAO<Student>  {

    private static final String STATEMENT_LOGIN = "SELECT id,email,password FROM admin WHERE email=? AND password=md5(?);";

    private final Admin admin;

    public AdminLoginDAO(final Connection con, final Admin admin) {
        super(con);
        this.admin = admin;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        // the results of the search
        Admin admin_new = null;
        try {
            stmnt = con.prepareStatement(STATEMENT_LOGIN);
            stmnt.setString(1, admin.getEmail());
            stmnt.setString(2, admin.getPassword());
            //stmnt.setLong(3, student.getProfleImage());

            rs = stmnt.executeQuery();


            if(rs.next()){

                admin_new = new Admin(rs.getLong(1),admin.getEmail().toLowerCase(),rs.getString(8),rs.getString(3),rs.getString(6),rs.getString(7), rs.getFloat(9), rs.getFloat(10), rs.getFloat(11),rs.getFloat(12) );
                LOGGER.info("Admin logged in {}.", admin_new.getEmail());

            }else{
                LOGGER.error("error logging in the student {}",admin.getEmail());

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
