package wascoot.rest;

import wascoot.database.*;
import wascoot.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Model} resource.
 *
 * @version 1.00
 * @since 1.00
 */

public final class ModelRestResource extends RestResource {
    /**
     * Creates a new REST resource for managing {@code Model} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public ModelRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(req, res, con);
    }

    /**
     * Lists all the models.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void getModelList() throws IOException {

        List<Model> el  = null;
        Message m = null;

        try{
            // creates a new object for accessing the database and lists all the employees
            el = new getModelListDatabase(con).getModelList();

            if(el != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot list models: unexpected error.", "E6A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search models: unexpected error.", "E6A2", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
