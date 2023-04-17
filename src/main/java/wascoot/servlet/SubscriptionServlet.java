package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerListDatabase;
import wascoot.database.SubscriptionListDatabase;
import wascoot.resource.Customer;
import wascoot.resource.Message;
import wascoot.resource.Subscription;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public final class SubscriptionServlet extends AbstractDatabaseServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Subscription> sl = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the models
            sl = new SubscriptionListDatabase(getDataSource().getConnection()).getSubscriptionList();

            m = new Message("Subscriptions successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for subscriptions.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for subscriptions: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the model list and the message as a request attribute
        req.setAttribute("subscriptionList", sl);
        req.setAttribute("message", m);

        // forwards the control to the search-employee-result JSP
        req.getRequestDispatcher("/jsp/subscription.jsp").forward(req, res);

    }
}
