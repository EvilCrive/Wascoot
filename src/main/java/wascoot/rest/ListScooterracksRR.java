package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.ScooterRackDatabase;
import wascoot.resource.Actions;
import wascoot.resource.Message;
import wascoot.resource.ResourceList;
import wascoot.resource.Scooterrack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public final class ListScooterracksRR extends AbstractRR {
    public ListScooterracksRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_ALL_SCOOTERRACKS,req, res, con);
    }

    @Override
    protected void doServe() throws IOException {

        List<Scooterrack> el = null;
        Message m = null;

        try {

            // creates a new DAO for accessing the database and lists the employee(s)
            el = new ScooterRackDatabase(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("Employee(s) successfully listed.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing employee(s).");

                m = new Message("Cannot list employee(s): unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list employee(s): unexpected database error.", ex);

            m = new Message("Cannot list employee(s): unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
