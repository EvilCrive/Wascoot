package wascoot.rest;

import wascoot.database.*;
import wascoot.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Model} resource.
 *
 * @version 1.00
 * @since 1.00
 */

public final class InsertModelRR extends AbstractRR {
    /**
     * Creates a new REST resource for managing {@code Model} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public InsertModelRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(Actions.INSERT_NEW_MODEL,req, res, con);
    }

    /**
     * Lists all the models.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    @Override
    protected void doServe() throws IOException{
        Message m = null;
        Model el = null;
        try {
            final Model model = Model.fromJSON(req.getInputStream());
            // DEFINE A NEW DAO QWHICH RECEIVES AS ARGUMENT THE model AND RETURNS A NEW MODEL AFTER INSERTION
            el = new InsertNewModelDAO(con,model).access().getOutputParam();
            if (el != null){
                // IN THIS CASE THE MODEL HAS BEEN CORRECTLY INSERTED. RETURN A JSON REPRESENTATION OF THE MODEL RETURNED
                res.setStatus(HttpServletResponse.SC_CREATED);
                el.toJSON(res.getOutputStream());
            }
            else if(el == null){
                // IF IT IS NULL THE INSERTION WAS NOT CORRECT HENCE HANDLE THIS CONDITION. MAY BE YOUCN RETURN A MESSAGE WITH THE ERROR THAT OCCURRED
            }

        }catch (EOFException ex){


        } catch (SQLException ex){

        }

    }
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
