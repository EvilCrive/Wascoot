package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerDAO;
import wascoot.resource.Customer;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListCustomerServlet extends AbstractDatabaseServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        // model
        List<Customer> el = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the models
            el = new CustomerDAO(getConnection()).access().getOutputParam();

            m = new Message("Customers successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for customers.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for customers: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("customerList", el);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/customer-list.jsp").forward(req, res);

    }
}
