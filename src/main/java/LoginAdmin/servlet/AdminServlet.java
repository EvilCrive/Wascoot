package LoginAdmin.servlet;

import LoginAdmin.dao.AdminLoginDAO;
import LoginAdmin.dao.AdminRegisterDAO;
//import LoginAdmin.dao.GetAdminByEmailDAO;

import LoginAdmin.resource.Administrator;
import LoginAdmin.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public final class AdminServlet extends AbstractDatabaseServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("LOGIN");

        String op = request.getRequestURI();
        op = op.substring(op.lastIndexOf("admin") + 6);
        LOGGER.error("op {}",op);


        switch (op){

            case "login/":
                request.getRequestDispatcher("/html/login.html").forward(request, response);
                break;
            case "signup/":
                request.getRequestDispatcher("/html/signup.html").forward(request, response);
                break;

            default:
                Message m = new Message("An error occurred default","E200","Operation Unknown");
                LOGGER.error("stacktrace {}:", m.getMessage());
        }




    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("LOGIN");
        String op = req.getRequestURI();


        op = op.substring(op.lastIndexOf("admin") + 6);
        LOGGER.info("op {}",op);

        switch (op){

            case "login/":
                // the requested operation is login
                loginOperations(req, res, false);
                break;

            case "signup/":
                // the requested operation is register
                registrationOperations(req, res);
                break;


            default:
                Message m = new Message("An error occurred default","E200","Operation Unknown");
                LOGGER.error("stacktrace {}:", m.getMessage());
        }
    }

/*
    public ArrayList<AdminEmail> getAdminEmail(){
        ArrayList<AdminEmail> ae;

        try {
            ae = new GetAdminByEmailDAO(getConnection()).access().getOutputParam();

            LOGGER.info("adminemail {}:", ae);

        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
//            writeError(res, ErrorCode.INTERNAL_ERROR);
            return null;
        }
        return ae;
    }
*/


    public void loginOperations(HttpServletRequest req, HttpServletResponse res, boolean isValid) throws ServletException, IOException {

        Administrator admin = null;
        Message m = null;


        String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";
        String regex_email  = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

        try {
            //take from the request, the parameters (email and password, course and masterdegree)
            String id = req.getParameter("id");
            String email = req.getParameter("email");
            String password = req.getParameter("password");


            /*
            //ArrayList<AdminEmail> adminemials = getAdminEmails();
            //ae = new GetAdminByEmailDAO(getConnection()).access().getOutputParam();
            //LOGGER.info("adminemail {}:", ae);

            //boolean found_admin = false;

            for(AdminEmail adminemial : adminemials){
                if (adminemial.getId().toString().equals(id)) {
                    found_admin = true;
                    break;
                }
            }
            LOGGER.info("admin: {} is trying to login",id);
            */

            if(isValid){
                id = id.toLowerCase();
                email = email.toLowerCase();
                password = password.toLowerCase();
                LOGGER.info("email to lower {} ",email);
                Administrator a = new Administrator(id, email, password);
                // try to find the user in the database
                admin = new AdminLoginDAO(getConnection(),a).access().getOutputParam();
                LOGGER.info("email to lower2 {}",admin);
                //matches
                if (admin == null){
                    //if not, tell it to the user
                    m = new Message("The admin does not exist","E200","Missing admin");
                    LOGGER.error("problems with admin: {}", m.getMessage());

                }
                else{
                    m = new Message("Login success");
                    LOGGER.info("the ADMIN {} LOGGED IN",admin.getEmail());

                }
            }else{
                if (email == null || email.equals("")) {
                    //the email is null (was not set on the parameters) or an empty string
                    //notify this to the user
                    m = new Message("Insert an email","E200","Missing fields");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                } else if (password == null || password.equals("")) {
                    //the password was empty

                    m = new Message("Insert the password", "E200", "Missing fields");
                    LOGGER.error("problems with fields: {}", m.getMessage());
                }

                // check password is compliant
                else if (!password.matches(regex_psw)){
                    m = new Message("This password is not compliant","E200","Password not compliant");

                    LOGGER.error("problems with fields: {}", m.getMessage());

                }
                // check email is compliant
                else if (!email.matches(regex_email)){
                    m = new Message("This is not an email","E200","Email not compliant");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                }

                else{
                    //try to authenticate the user
                    email = email.toLowerCase();
                    LOGGER.info("email to lower {} {} {}",id,email,password);
                    Administrator a = new Administrator(id, email, password);
                    // try to find the user in the database
                    admin = new AdminLoginDAO(getConnection(),a).access().getOutputParam();
                    LOGGER.info("email to lower2 {}",admin);



                    //the UserDAO will tell us if the email exists and the password
                    //matches
                    if (admin == null){
                        //if not, tell it to the user
                        m = new Message("The admin does not exist","E200","Missing admin");
                        LOGGER.error("problems with admin: {}", m.getMessage());

                    }
                    else{
                        m = new Message("Login success");
                        LOGGER.info("the ADMIN {} LOGGED IN",admin.getId());

                    }
                }
            }

        } catch (SQLException e){
            m = new Message("An error occurred SQL","E200",e.getMessage());
            LOGGER.error("stacktrace:", e);
        }
        catch (NumberFormatException e){
            m = new Message("An error occurred handling numbers","E200",e.getMessage());
            LOGGER.error("stacktrace:", e);
        }

        finally{
            writePage(admin,m,res);
        }
    }


    public void registrationOperations(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Administrator admin = null;
        Message m = null;
        try {

            //get the registration patameters
            String id = req.getParameter("id");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Administrator a = new Administrator (id, email, password);
            admin = new AdminRegisterDAO(getConnection(),a).access().getOutputParam();


            LOGGER.info("admin {} is trying to register", id);
            // regex to validate email and password
            String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";
            String regex_email = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

            //check that all registrations parameters have been set and are not null
            if (email == null || email.equals("") ||
                    password == null || password.equals("")) {

                m = new Message("Some fields are empty", "E200", "Missing fields");
                LOGGER.error("problems with fields: {}", m.getMessage());

            }
            // check password is compliant
            else if (!password.matches(regex_psw)) {
                m = new Message("This password is not compliant", "E200", "Password not compliant");

                LOGGER.error("problems with fields: {}", m.getMessage());

            }
            // check email is compliant
            else if (!email.matches(regex_email)) {
                m = new Message("This is not an email", "E200", "Email not compliant");
                LOGGER.error("problems with fields: {}", m.getMessage());

            }

            else {
                LOGGER.error("problems with fields: {}", m.getMessage());
            }


            } catch (SQLException | ServletException e) {

            m = new Message("An error occurred SQL, SERVLET","E200",e.getMessage());
            LOGGER.error("stacktrace:", e);

            }
            catch (NumberFormatException e){
            m = new Message("An error occurred handling numbers","E200",e.getMessage());
            LOGGER.error("stacktrace:", e);
            }
            finally{
                if (m != null){
                    loginOperations(req, res, true);
                    writePage(admin,m,res);
                }
            }

    }

    public void writePage(Administrator a, Message m, HttpServletResponse res) throws IOException{

        try{
            if(m == null) {
                m = new Message("An error occurred - null", "E200", "Unknown error");
            }
            // set the MIME media type of the response
            res.setContentType("text/html; charset=utf-8");

            // get a stream to write the response
            PrintWriter out = res.getWriter();

            // write the HTML page
            out.printf("<!DOCTYPE html>%n");

            out.printf("<html lang=\"en\">%n");
            out.printf("<head>%n");
            out.printf("<meta charset=\"utf-8\">%n");
            out.printf("<title>Login</title>%n");
            out.printf("</head>%n");

            out.printf("<body>%n");


            if (m.isError()) {
                out.printf("<h1>LOGIN ADMIN - ERROR</h1>%n");
                out.printf("<hr/>%n");
                out.printf("<ul>%n");
                out.printf("<li>error code: %s</li>%n", m.getErrorCode());
                out.printf("<li>message: %s</li>%n", m.getMessage());
                out.printf("<li>details: %s</li>%n", m.getErrorDetails());
                out.printf("</ul>%n");
            } else {
                out.printf("<h1>LOGIN ADMIN - SUCCESS</h1>%n");
                out.printf("<hr/>%n");
                out.printf("<p>%s</p>%n", m.getMessage());
                out.printf("<ul>%n");
                out.printf("<li>email: %s</li>%n", a.getEmail());
                out.printf("</ul>%n");
            }

            out.printf("</body>%n");

            out.printf("</html>%n");

            // flush the output stream buffer
            out.flush();

            // close the output stream
            out.close();
        } catch (IOException ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when logging admin %d.", a.getEmail()), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }


    }

}






