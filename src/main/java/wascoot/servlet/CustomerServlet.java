package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerListDatabase;

import wascoot.resource.Customer;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public final class CustomerServlet extends AbstractDatabaseServlet{

    /**
     * get models
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Customer> cl = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the models
            cl = new CustomerListDatabase(getDataSource().getConnection()).getCustomerList();

            m = new Message("Customers successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for customers.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for customers: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("customerList", cl);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/customer.jsp").forward(req, res);

    }

}

