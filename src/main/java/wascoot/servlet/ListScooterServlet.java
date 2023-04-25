package wascoot.servlet;

import wascoot.database.ListScootersDAO;
import wascoot.database.ScooterRackDatabase;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Dashboard Servlet
 *
 * @version 1.00
 * @since 1.00
 */
public final class ListScooterServlet extends AbstractDatabaseServlet {

    /**
     * Scooter servlet
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

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.LIST_SCOOTERS);

        List<Scooter> sl = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the scooters
            sl = new ListScootersDAO(getDataSource().getConnection()).getScootersList();

            m = new Message("Scooters successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for the scooters in the db.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for the scooters: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the lists and the message as a request attribute
        req.setAttribute("scootersList", sl);
        req.setAttribute("message", m);

        // forwards the control to the scooterracks.jsp
        req.getRequestDispatcher("/jsp/scooter-list.jsp").forward(req, res);

    }
}
