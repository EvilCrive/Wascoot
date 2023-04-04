package it.unipd.dei.webapp.LoginRegister.servlet;

import it.unipd.dei.webapp.LoginRegister.dao.*;
import it.unipd.dei.webapp.LoginRegister.resource.*;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends AbstractDatabaseServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("LOGIN");

        String op = request.getRequestURI();
        op = op.substring(op.lastIndexOf("admin") + 8);
        LOGGER.error("op {}",op);


        switch (op){

            case "login/":
                request.getRequestDispatcher("/html/adminlogin.html").forward(request, response);
                break;
            case "signup/":
                request.getRequestDispatcher("/html/adminsignup.html").forward(request, response);
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


        op = op.substring(op.lastIndexOf("admin") + 8);
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


    public ArrayList<Admin> getAdminEmail(){
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



    public void loginOperations(HttpServletRequest req, HttpServletResponse res, boolean isValid) throws ServletException, IOException {

        Admin Admin = null;
        Message m = null;

        String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";
        String regex_email  = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

        try {
            //take from the request, the parameters (email and password, course and masterdegree)
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            ArrayList<Admin> admins = getAdmins();
            boolean found_admin = false;

            for(Admin admin : admins){
                if (admin.getId().toString().equals(id)) {
                    found_admin = true;
                    break;
                }
            }
            LOGGER.info("admin: {} is trying to login",email);

            if(isValid){
                email = email.toLowerCase();
                LOGGER.info("email to lower {} {} ",email, id);
                Admin u = new Admin(email,password,id);
                // try to find the user in the database
                admin = new AdminLoginDAO(getConnection(),u).access().getOutputParam();
                LOGGER.info("email to lower2 {}",admin);
                //the UserDAO will tell us if the email exists and the password
                //matches
                if (admin == null){
                    //if not, tell it to the user
                    m = new Message("The admin does not exist","E200","Missing admin");
                    LOGGER.error("problems with student: {}", m.getMessage());

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

                    m = new Message("Insert the password","E200","Missing fields");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                else if (!found_m) {
                    //the password was empty
                    m = new Message("Master not found","E200","Missing fields");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                }
                else if (!found_c) {
                    //the password was empty
                    m = new Message("Course not found","E200","Missing fields");
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
                    LOGGER.info("email to lower {} {} {}",email,id);
                    Admin u = new Admin(email,password, id);
                    // try to find the user in the database
                    Admin = new AdminLoginDAO(getConnection(),u).access().getOutputParam();
                    LOGGER.info("email to lower2 {}",admin);



                    //the UserDAO will tell us if the email exists and the password
                    //matches
                    if (admin == null){
                        //if not, tell it to the user
                        m = new Message("The admin does not exist","E200","Missing admin");
                        LOGGER.error("problems with student: {}", m.getMessage());

                    }
                    else{
                        m = new Message("Login success");
                        LOGGER.info("the ADMIN {} LOGGED IN",admin.getEmail());

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

        Admin admin = null;
        Message m = null;
        try {
            // auxiliary method to retrieve the master degrees in the database
            ArrayList<MasterDegree> masterdegrees = getMasterDegrees();
            ArrayList<Course> courses = getCourses();


            //get the registration patameters
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            boolean found_m = false;
            boolean found_c = false;
            for(Course course : courses){
                if (course.getId().toString().equals(courseid)) {
                    found_c = true;
                    break;
                }
            }
            for(MasterDegree master : masterdegrees){
                if (master.getId().toString().equals(masterid)) {
                    found_m = true;
                    break;
                }
            }
            LOGGER.info("student {} is trying to register",email);
            // regex to validate email and password
            String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";
            String regex_email  = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

            //check that all registrations parameters have been set and are not null
            if (email==null||email.equals("")||
                    password==null||password.equals("")||
                    courseid==null||courseid.equals("")||
                    masterid==null||masterid.equals("")||
                    name==null||name.equals("")||
                    surname==null||surname.equals("")){

                m = new Message("Some fields are empty","E200","Missing fields");
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
            else if (!found_m) {
                //the password was empty
                m = new Message("Master not found","E200","Missing fields");
                LOGGER.error("problems with fields: {}", m.getMessage());

            }
            else if (!found_c) {
                //the password was empty
                m = new Message("Course not found","E200","Missing fields");
                LOGGER.error("problems with fields: {}", m.getMessage());

            }

            else {

                // deadlines for registration, group creation and assessment --> we use the first one
                ArrayList<ArrayList<LocalDate>> deadelines ;
                deadelines = new GetDeadlinesDAO(getConnection(),Long.parseLong(courseid)).access().getOutputParam();
                LocalDate now = LocalDate.now();
                ArrayList<LocalDate> regdeadline = deadelines.get(0);
                boolean expired = !((now.isAfter(regdeadline.get(0)) | now.isEqual(regdeadline.get(0))) & (now.isBefore(regdeadline.get(1)) ));

                // all the parameters are correct
                // check that the course is in the study plan of a mg
                boolean course_belongs_master = new CheckCourseMasterDAO(getConnection(),Long.parseLong(courseid),masterid).access().getOutputParam();
                // initialize a new Student
                student = new Student(email,password,masterid,Long.parseLong(courseid));
                // check if the student is already enrolled for this course
                boolean student_exists_for_this_course = new GetStudentByEmailDAO(getConnection(),student).access().getOutputParam();
                if(!course_belongs_master){
                    // the course is not comprised in the master degree study plan of the student
                    m = new Message("This course is not in the master degree study plan","E200","Course not allowed");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                }
                else if(student_exists_for_this_course){
                    // the student is already signed up for this course
                    m = new Message("This student already exists","E200","Student already existing");
                    LOGGER.error("problems with fields: {}", m.getMessage());

                }

                else{
                    email = email.toLowerCase();

                    //else, create a new user resource
                    Student student_to_reg = new Student(email,badge,password,masterid,Long.parseLong(courseid),name,surname);
                    //pass it to the dao to register it
                    if(!expired){
                        short cfu = new GetCourseCFUDAO(getConnection(),student_to_reg).access().getOutputParam();
                        new StudentRegisterDAO(getConnection(),student_to_reg,cfu).access().getOutputParam();
                        LOGGER.info("REGISTERED STUDENT {} {} {}",email,courseid,masterid);

                        //if the registration ended correctly, forward the user to the
                        //login service: note that, now the login service will login the user
                        //and create the session. We are not redirecting the user to the
                        //login page
                        loginOperations(req,res, true);

                    }else{

                            m = new Message("The deadline is expired","E200","Deadline expired");
                            LOGGER.error("problems with fields: {}", m.getMessage());



                    }


                }

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
                writePage(student,m,res);
            }
        }

    }

    public void writePage(Student s,Message m, HttpServletResponse res) throws IOException{

        try{
            if(m == null){
                m = new Message("An error occurred - null","E200","Unknown error");
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
                out.printf("<h1>LOGIN STUDENT - ERROR</h1>%n");
                out.printf("<hr/>%n");
                out.printf("<ul>%n");
                out.printf("<li>error code: %s</li>%n", m.getErrorCode());
                out.printf("<li>message: %s</li>%n", m.getMessage());
                out.printf("<li>details: %s</li>%n", m.getErrorDetails());
                out.printf("</ul>%n");
            } else {
                out.printf("<h1>LOGIN STUDENT - SUCCESS</h1>%n");
                out.printf("<hr/>%n");
                out.printf("<p>%s</p>%n", m.getMessage());
                out.printf("<ul>%n");
                out.printf("<li>badge: %s</li>%n", s.getBadge());
                out.printf("<li>surname: %s</li>%n", s.getSurname());
                out.printf("<li>name: %s</li>%n", s.getName());
                out.printf("<li>email: %s</li>%n", s.getEmail());
                out.printf("</ul>%n");
            }

            out.printf("</body>%n");

            out.printf("</html>%n");

            // flush the output stream buffer
            out.flush();

            // close the output stream
            out.close();
        } catch (IOException ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when logging student %d.", s.getEmail()), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }


    }

}






