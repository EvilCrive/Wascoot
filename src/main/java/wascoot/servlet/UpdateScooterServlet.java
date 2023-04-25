package wascoot.servlet;

import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.ScooterRackDatabase;
import wascoot.database.UpdateScooterDAO;
import wascoot.database.UpdateScooterrackDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Locale;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public final class UpdateScooterServlet extends AbstractDatabaseServlet {


    /**
     * @param req request
     *            id
     *            date_of_purchase
     *            km_traveled
     *            model
     *            remaining_battery_life
     *            id_scooterrack
     *
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SCOOTERRACK);

        Integer id = null;
        java.util.Date dateOfPurchase = null;
        Double kmTraveled = null;
        String model = null;
        Double remainingBatteryLife = null;
        Integer idScooterrack = null;

        Scooter s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            id = Integer.parseInt(req.getParameter("id"));
            dateOfPurchase = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("date_of_purchase"));
            kmTraveled = Double.parseDouble(req.getParameter("km_traveled"));
            model = req.getParameter("model");
            remainingBatteryLife = Double.parseDouble(req.getParameter("remaining_battery_life"));
            idScooterrack = Integer.parseInt(req.getParameter("id_scooterrack"));

            java.sql.Date dateOfPurchase_sql = new java.sql.Date(dateOfPurchase.getTime());

            LogContext.setResource(req.getParameter("id"));

            // creates a new scooter from the request parameters
            s = new Scooter(id, dateOfPurchase_sql, kmTraveled, model, remainingBatteryLife, idScooterrack);

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
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            // stores the scooterrack and the message as a request attribute
            req.setAttribute("updateScooter", s);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/update-scooter.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooter %s.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }
}
