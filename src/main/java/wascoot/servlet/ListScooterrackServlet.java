package wascoot.servlet;

import wascoot.database.ScooterRackDatabase;
import wascoot.resource.Scooterrack;

import wascoot.resource.Message;

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
public final class ListScooterrackServlet extends AbstractDatabaseServlet {

    /**
     * Scooter Racks servlet
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

        List<Scooterrack> el = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the scooterracks and paymentwithoutsubscription
            el = new ScooterRackDatabase(getDataSource().getConnection()).getScooterRackList();

            m = new Message("Scooter Racks successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for the scooter racks in the db.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for the scooter racks: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the lists and the message as a request attribute
        req.setAttribute("scooterrackList", el);
        req.setAttribute("message", m);

        // forwards the control to the scooterracks.jsp
        req.getRequestDispatcher("/jsp/scooterrack-list.jsp").forward(req, res);

    }
}
