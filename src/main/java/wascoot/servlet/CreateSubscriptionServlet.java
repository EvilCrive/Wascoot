package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateSubscriptionDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

public final class CreateSubscriptionServlet extends AbstractDatabaseServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.CREATE_SUBSCRIPTION);

        //int id = -1;
        String type = null;
        int dailyUnlocks = -1;
        String validityPerDay = null;
        double fixedPrice = -1;


        Subscription e = null;
        Message m = null;

        try {
            // retrieves the request parameters

            type = req.getParameter("type");
            dailyUnlocks =Integer.parseInt(req.getParameter("daily_unlocks"));
            //batteryLife = LocalTime.parse(req.getParameter("battery_life"));
            validityPerDay = req.getParameter("validity_per_day");
            fixedPrice = Double.parseDouble(req.getParameter("fixed_price"));

            // set the badge of the employee as the resource in the log context
            // at this point we know it is a valid integer
            LogContext.setResource(req.getParameter("type"));

            // creates a new employee from the request parameters
            e = new Subscription(type, dailyUnlocks, Time.valueOf(validityPerDay), fixedPrice);

            // creates a new object for accessing the database and stores the employee
            new CreateSubscriptionDAO(getConnection(), e).access();

            m = new Message(String.format("Subscription %s successfully created.", type));

            LOGGER.info("Model %s successfully created in the database.", type);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the model. Invalid input parameters: badge, age, and salary must be integer.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the model. Invalid input parameters: badge, age, and salary must be integer.",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot create the subscription: employee %s already exists.", type), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot create the employee: employee %s already exists.", type),
                        ex);
            } else {
                m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
            }
        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("newSubscription", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/create-subscription-result.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating model %s.", type), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
