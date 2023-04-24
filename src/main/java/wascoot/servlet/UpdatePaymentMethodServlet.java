package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.UpdateModelDAO;
import wascoot.database.UpdatePaymentMethodDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

public final class UpdatePaymentMethodServlet extends AbstractDatabaseServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_PAYMENT_METHOD);

        // request parameters
		/*int badge = -1;
		String surname = null;
		int age = -1;
		int salary = -1;*/
        //int id = -1;
        String type = null;
        //LocalTime batteryLife = null;
        String activation = null;


        // model
        PaymentMethod e = null;
        Message m = null;

        try {
            // retrieves the request parameters


            //batteryLife = LocalTime.parse(req.getParameter("battery_life"));
            activation = req.getParameter("activation");
            type = req.getParameter("type");

            // set the badge of the employee as the resource in the log context
            // at this point we know it is a valid integer
            LogContext.setResource(req.getParameter("type"));

            // creates a new employee from the request parameters
            e = new PaymentMethod( type, activation);

            // creates a new object for accessing the database and stores the employee
            new UpdatePaymentMethodDAO(getConnection(), e).access();

            m = new Message(String.format("Model %s successfully updated.", type));

            LOGGER.info("Model %s successfully updated in the database.", type);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the employee. Invalid input parameters: badge, age, and salary must be integer.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the employee. Invalid input parameters: badge, age, and salary must be integer.",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot update the payment method: payment method %s already exists.", type), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot update the employee: employee %s already exists.", type),
                        ex);
            } else {
                m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
            }
        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("updatePaymentMethod", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/update-paymentmethod.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating employee %s.", type), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
