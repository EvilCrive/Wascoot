package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.ListModelDAO;
import wascoot.database.ListPaymentMethodDAO;
import wascoot.resource.Model;
import wascoot.resource.Message;
import wascoot.resource.PaymentMethod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListPaymentMethod extends AbstractDatabaseServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        // model
        List<PaymentMethod> el = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the models
            el = new ListPaymentMethodDAO(getConnection()).access().getOutputParam();

            m = new Message("Models successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for models.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for models: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("paymentList", el);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/payment-list.jsp").forward(req, res);

    }
}
