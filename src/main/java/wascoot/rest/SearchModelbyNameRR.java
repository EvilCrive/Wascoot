package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.ResourceList;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SearchModelbyNameRR {
    package GroupCreation.rest;

import GroupCreation.dao.GetModelByNameDAO;
import GroupCreation.dao.GetModelByBrandDAO;
import GroupCreation.resource.Actions;
import GroupCreation.resource.Message;
import GroupCreation.resource.ResourceList;
import GroupCreation.resource.Model;
import GroupCreation.servlet.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

    /**
     * A REST resource for searching {@link Model}s by client.
    public final class SearchModelByNameRR extends AbstractRR {

        /**
         * Creates a new REST resource for searching {@code Model}s by client.
         *
         * @param req the HTTP request.
         * @param res the HTTP response.
         * @param con the connection to the database.
         */
        public SearchModelbyNameRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
            super(Actions.GET_Model_BY_Name, req, res, con);
        }


    @Override

        protected void doServe() throws IOException {

            ArrayList<Model> el = null;
            Message m = null;

            try {
                // parse the URI path to extract the salary

                String path = req.getRequestURI();
                path = path.substring(path.lastIndexOf("course") + 7);

                final Long id_s = Long.parseLong(path);

                LogContext.setResource(path);

                // creates a new DAO for accessing the database and searches the Student(s)
                el = new GetModelBynameDAO(con,id_s).access().getOutputParam();
                LOGGER.info("Model",name);

                    res.setStatus(HttpServletResponse.SC_OK);
                    new ResourceList<>(el).toJSON(res.getOutputStream());
                }

                    m = new Message("0 model for this name.");
                    res.setStatus(HttpServletResponse.SC_OK);
                    m.toJSON(res.getOutputStream());
                }


                else { // it should not happen
                    LOGGER.error("Fatal error while searching model(s) for the name %d.",id_s);

                    m = new Message("Errors reading name for this model.", "E5A1", null);
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    m.toJSON(res.getOutputStream());
                }
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
              LOGGER.warn("Cannot search name(s): wrong format for URI /name/model.", ex);

                m = new Message("Cannot search name(s): wrong format for URI /name/model/.", "E4A7",
                        ex.getMessage());
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
            } catch (SQLException ex) {
                LOGGER.error("Cannot search model(s): unexpected database error.", ex);

                m = new Message("Cannot search model(s): unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }


    }

}
