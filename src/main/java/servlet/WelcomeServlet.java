package GroupCreation.servlet;

import GroupCreation.dao.GetStudentsWithGroupDAO;
import GroupCreation.resource.Message;
import GroupCreation.resource.Student;
import GroupCreation.resource.Actions;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "WelcomeServlet", value = "")
public class WelcomeServlet extends AbstractDatabaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Student> students = null;
        LogContext.setIPAddress(request.getRemoteAddr());
        try {
            students = new GetStudentsWithGroupDAO(getConnection()).access().getOutputParam();
            LogContext.setResource("web app");
            LOGGER.info("students {}",students);
            request.setAttribute("students",students);
        } catch (SQLException e) {
            Message m = new Message(
                    "ERROR",
                    "E100", "ERROR");
            // in catch blocks I forward to the same page!!
            request.setAttribute("message",m);
            LOGGER.error(
                    "An error occured in group get.");
        }
        finally {
            request.getRequestDispatcher("jsp/welcome.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
