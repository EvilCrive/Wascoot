package wascoot.servlet;

import wascoot.resource.LogContext;
import wascoot.rest.Model;

package wascoot.servlet;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.ScooterDatabase;
import wascoot.database.UpdateScooterDAO;
import wascoot.resource.Scooter;

import wascoot.resource.Message;
import wascoot.resource.LogContext;
import wascoot.resource.Actions;

//import IOException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ScooterServelet {

}

    public final class UpdateScooterServlet extends AbstractDatabaseServlet {
        /**
         * +     * @param req
         * +     *            id
         * +     *            date of purchase
         * +     *            km travel
         * +     *            model
         * +     *            remaining bttery life
         * +     *
         * +     * @param res
         * +     * @throws ServletException
         * +     * @throws IOException
         * +
         */

        @Override


        public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            LogContext.setIPAddress(req.getRemoteAddr());
            LogContext.setAction(Actions.UPDATE_SCOOTER);

            Integer id = null;
            Integer dateOfPurchase = null;
            Integer kmTravel = null;
            String model = null;
            String remainingBatteryLife = null;

            Scooter s = null;
            Message m = null;

            try {
                // retrieves the request parameters

                id = Integer.parseInt(req.getParameter("id"));
                dateOfPurchase = Integer.parseInt(req.getParameter("dateOfPurchase"));
                kmTravel = Integer.parseInt(req.getParameter("kmTravel"));
                Model = req.getParameter("Model");
                remainingBAtteryLife = req.getParameter("remainingBAtteryLife");
                // set the id of the scooter as the resource in the log context
                // at this point we know it is a valid integer

                LogContext.setResource(req.getParameter("id"));

                // creates a new scooter from the request parameters
                s = new Scooter(id, dateOgPurchase, kmTravel, model, remainingBatteryLife);

                // creates a new object for accessing the database and stores the employee
                new UpdateScooterDAO(getConnection(), s).access();

                m = new Message(String.format("Scooter %s successfully updated.", id));

                LOGGER.info("Scooter %s successfully updated in the database.", id);


            } catch (NumberFormatException ex) {
                m = new Message(
                        "Cannot create the scooter. Invalid input parameters",
                        "E100", ex.getMessage());

                LOGGER.error(
                        "Cannot create the scooter. Invalid input parameters",
                        ex);

            } catch (SQLException ex) {
                if ("23505".equals(ex.getSQLState())) {
                    m = new Message(String.format("Cannot update the scooter: scooter %s already exists.", id), "E300",
                            ex.getMessage());
                    LOGGER.error(
                            new StringFormattedMessage("Cannot update the scooter: scooter %s already exists.", id),
                            ex);

                } else {
                    m = new Message("Cannot create the scooter: unexpected error while accessing the database.", "E200",
                            ex.getMessage());

                    LOGGER.error("Cannot create the scooter: unexpected error while accessing the database.", ex);

                }

            }

            try {
                // stores the scooter and the message as a request attribute
                req.setAttribute("updateScooter", s);
                req.setAttribute("message", m);

                // forwards the control to the create-employee-result JSP
                req.getRequestDispatcher("/jsp/update-scooter.jsp").forward(req, res);

            } catch (Exception ex) {
                LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooter %s.", id), ex);
                throw ex;

            } finally {
                LogContext.removeIPAddress();
                LogContext.removeAction();
                LogContext.removeResource();

            }

        }

    }
}
