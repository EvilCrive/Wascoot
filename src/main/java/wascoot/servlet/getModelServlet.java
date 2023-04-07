package wascoot.servlet;

import wascoot.database.getModelListDatabase;
import wascoot.resource.Model;
import wascoot.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * get models
 *
 * @version 1.00
 * @since 1.00
 */
public final class getModelServlet extends AbstractDatabaseServlet {

    /**
     * get models
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        // model
        List<Model> el = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the models
            el = new getModelListDatabase(getDataSource().getConnection()).getModelList();

            m = new Message("Models successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for models.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for models: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("modelList", el);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/model.jsp").forward(req, res);

    }

}
