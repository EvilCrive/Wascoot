package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.PaymentMethodListDatabase;

import wascoot.resource.Message;

import wascoot.resource.PaymentMethod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public final class PaymentMethodServlet extends AbstractDatabaseServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        // model
        List<PaymentMethod> pl = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the paymentMethods
            pl = new PaymentMethodListDatabase(getDataSource().getConnection()).getPaymentMethodList();

            m = new Message("Payment Method successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for payment method.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for payment method: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("paymentMethodList", pl);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/paymentMethod.jsp").forward(req, res);
    }
}
