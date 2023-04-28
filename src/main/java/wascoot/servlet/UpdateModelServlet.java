package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.UpdateModelDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

public final class UpdateModelServlet extends AbstractDatabaseServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_MODEL);

        // request parameters
		/*int badge = -1;
		String surname = null;
		int age = -1;
		int salary = -1;*/
        String name = null;
        String brand = null;
        //LocalTime batteryLife = null;
        String batteryLife = null;
        double pricePerMin = -1;

        // model
        Model e = null;
        Message m = null;

        try {
            // retrieves the request parameters

            brand = req.getParameter("brand");
            //batteryLife = LocalTime.parse(req.getParameter("battery_life"));
            batteryLife = req.getParameter("battery_life");
            pricePerMin = Double.parseDouble(req.getParameter("price_per_min"));
            name = req.getParameter("name");

            // set the badge of the employee as the resource in the log context
            // at this point we know it is a valid integer
            LogContext.setResource(req.getParameter("name"));

            // creates a new employee from the request parameters
            e = new Model(name, brand, Time.valueOf(batteryLife), pricePerMin);

            // creates a new object for accessing the database and stores the employee
            new UpdateModelDAO(getConnection(), e).access();

            m = new Message(String.format("Model %s successfully updated.", name));

            LOGGER.info("Model %s successfully updated in the database.", name);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the employee. Invalid input parameters: badge, age, and salary must be integer.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the employee. Invalid input parameters: badge, age, and salary must be integer.",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot update the employee: employee %s already exists.", name), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot update the employee: employee %s already exists.", name),
                        ex);
            } else {
                m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
            }
        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("updateModel", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/update-model.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating employee %s.", name), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
