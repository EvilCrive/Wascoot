package wascoot.servlet;

import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.ScooterRackDatabase;
import wascoot.database.UpdateScooterrackDAO;
import wascoot.resource.Scooterrack;

import wascoot.resource.Message;
import wascoot.resource.LogContext;
import wascoot.resource.Actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public final class UpdateScooterrackServlet extends AbstractDatabaseServlet {


    /**
     * @param req
     *            id
     *            total parking spots
     *            available parking spots
     *            postal code
     *            address
     *
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SCOOTERRACK);

        Integer id = null;
        Integer totalParkingSpots = null;
        Integer availableParkingSpots = null;
        String postalCode = null;
        String address = null;

        Scooterrack s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            id = Integer.parseInt(req.getParameter("id"));
            totalParkingSpots = Integer.parseInt(req.getParameter("total_parking_spots"));
            availableParkingSpots = Integer.parseInt(req.getParameter("available_parking_spots"));
            postalCode = req.getParameter("postalcode");
            address = req.getParameter("address");

            // set the id of the scooterrack as the resource in the log context
            // at this point we know it is a valid integer

            LogContext.setResource(req.getParameter("id"));

            // creates a new scooterrack from the request parameters
            s = new Scooterrack(id, totalParkingSpots, availableParkingSpots, postalCode, address);

            // creates a new object for accessing the database and stores the employee
            new UpdateScooterrackDAO(getConnection(), s).access();

            m = new Message(String.format("Scooterrack %s successfully updated.", id));

            LOGGER.info("Scooterrack %s successfully updated in the database.", id);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooterrack. Invalid input parameters",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooterrack. Invalid input parameters",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot update the scooterrack: scooterrack %s already exists.", id), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot update the scooterrack: scooterrack %s already exists.", id),
                        ex);
            } else {
                m = new Message("Cannot create the scooterrack: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the scooterrack: unexpected error while accessing the database.", ex);
            }
        }

        try {
            // stores the scooterrack and the message as a request attribute
            req.setAttribute("updateScooterrack", s);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/update-scooterrack.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooterrack %s.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }
}
