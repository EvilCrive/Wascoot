package GroupCreation.servlet;

import GroupCreation.dao.GroupInfoDAO;
import GroupCreation.dao.GroupMembersDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import GroupCreation.dao.CreateGroupDAO;
import GroupCreation.dao.GetStudentsWithGroupDAO;
import GroupCreation.resource.Group;
import GroupCreation.resource.Student;
import GroupCreation.resource.Message;
import GroupCreation.resource.Actions;
import jakarta.activation.MimeTypeParseException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import GroupCreation.utils.*;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends AbstractDatabaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get both student and group info
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setAction(Actions.STATEMENT_GROUP_INFO);
        // At this point I need to retrieve students' and group info!
        try {
            String name = request.getParameter("groupnamesearch");
            LogContext.setResource(name);

            Group group = new GroupInfoDAO(getConnection(),name).access().getOutputParam();

            if(group == null){
                Message m = new Message(
                        "group not found",
                        "E400", "Group not found");
                request.setAttribute("message",m);
                // Neeeded to fill up the list of users without a group
                ArrayList<Student> students = new GetStudentsWithGroupDAO(getConnection()).access().getOutputParam();
                LOGGER.info("students {}",students);
                request.setAttribute("students",students);
                request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);

            }else{
                ArrayList<Student> members = new GroupMembersDAO(getConnection(),group).access().getOutputParam();
                request.setAttribute("members",members);
                request.setAttribute("group",group);
                request.getRequestDispatcher("/jsp/groupinfo.jsp").forward(request, response);

            }



        } catch (SQLException e) {
            Message m = new Message(
                    "An error occurred.",
                    "E400", e.getMessage());
            request.setAttribute("message",m);

            request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Group group = (Group) request.getAttribute("group");
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setAction(Actions.STATEMENT_GROUP_CREATION);
        LOGGER.info("group name POST FORWARD {}",group.getName());
        LOGGER.info("group desc POST FORWARD {}",group.getDescription());

        // At this point I need to retrieve students' info!
        try {
            ArrayList<Student> members = new GroupMembersDAO(getConnection(),group).access().getOutputParam();
            request.setAttribute("members",members);
            request.getRequestDispatcher("/jsp/groupinfo.jsp").forward(request, response);
            LogContext.setResource(group.getName());

        } catch (SQLException e) {
            Message m = new Message(
                    "An error occurred.",
                    "E400", e.getMessage());
            request.setAttribute("message",m);

            request.getRequestDispatcher("jsp/groupinfo.jsp").forward(request, response);
        }


    }
}
