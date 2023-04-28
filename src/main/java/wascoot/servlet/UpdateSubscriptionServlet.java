package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.UpdateSubscriptionDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

public final class UpdateSubscriptionServlet extends AbstractDatabaseServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SUBSCRIPTION);

        // request parameters
        int id = -1;
        String type = null;
        int dailyUnlocks = -1;
        String validityPerDay = null;
        double fixedPrice = -1;

        // model
        Subscription e = null;
        Message m = null;

        try {
            // retrieves the request parameters
            id = Integer.parseInt(req.getParameter("id"));
            type = req.getParameter("type");
            dailyUnlocks = Integer.parseInt(req.getParameter("daily_unlocks"));
            //batteryLife = LocalTime.parse(req.getParameter("battery_life"));
            validityPerDay = req.getParameter("validity_per_day");
            fixedPrice = Double.parseDouble(req.getParameter("fixed_price"));

            // set the badge of the employee as the resource in the log context
            // at this point we know it is a valid integer
            LogContext.setResource(req.getParameter("id"));

            // creates a new employee from the request parameters
            e = new Subscription(id,type, dailyUnlocks, Time.valueOf(validityPerDay), fixedPrice);

            // creates a new object for accessing the database and stores the employee
            new UpdateSubscriptionDAO(getConnection(), e).access();

            m = new Message(String.format("Subscription with id %d successfully updated.", id));

            LOGGER.info("Subscription with id %d successfully updated in the database.", id);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot update the subscription. Invalid input parameters: id, daily unlocks must be integer and fixed price should be double.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot update the subscription. Invalid input parameters: id, daily unlocks must be integer and fixed price should be double.",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot update the subscription: subscription %d already exists.", id), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot update the subscription: subscription %d already exists.", id),
                        ex);
            } else {
                m = new Message("Cannot update the subscription: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot update the subscription: unexpected error while accessing the database.", ex);
            }
        }

        try {

            req.setAttribute("updateSubscription", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/update-subscription.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating employee %d.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
