package wascoot.servlet;

import wascoot.rest.*;
import wascoot.resource.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;


public final class RestDispatcherServlet extends AbstractDatabaseServlet {

    /**
     * The JSON UTF-8 MIME media type
     */
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {

        LogContext.setIPAddress(req.getRemoteAddr());

        final OutputStream out = res.getOutputStream();

        try {

            // if the requested resource was an Student, delegate its processing and return
            if (processModel(req, res)) {
                return;
            }


            // if none of the above process methods succeeds, it means an unknown resource has been requested
            LOGGER.warn("Unknown resource requested: %s.", req.getRequestURI());

            final Message m = new Message("Unknown resource requested.", "E4A6",
                    String.format("Requested resource is %s.", req.getRequestURI()));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.setContentType(JSON_UTF_8_MEDIA_TYPE);
            m.toJSON(out);
        } catch (Throwable t) {
            LOGGER.error("Unexpected error while processing the REST resource.", t);

            final Message m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(out);
        } finally {

            // ensure to always flush and close the output stream
            if (out != null) {
                out.flush();
                out.close();
            }

            LogContext.removeIPAddress();
        }
    }


    /**
     * Checks whether the request if for an {@link Student} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     *
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     *
     * @throws Exception if any error occurs.
     */
    private boolean processModel(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

        final String method = req.getMethod();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a student
        if (path.lastIndexOf("rest/model/") <= 0) {
            return false;
        }




        path = path.substring(path.lastIndexOf("model/") + 6);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");



        // the request URI is: /student
        // if method GET, list students
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ListModelRR(req, res, getConnection()).serve();
                    break;
                case "POST":
                    new InsertModelRR(req,res,getConnection()).serve();
                case "DELETE":
                    new DeleteModelRR(req,res,getConnection()).serve();
                default:
                    LOGGER.warn("Unsupported operation for URI /model: %s.", method);

                    m = new Message("Unsupported operation for URI /model.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        }else{
            // IN THIS CASE I HAVE AN URI SUCH AS /rest/model/{id_mode}
            // we can use get method to retrieve the information about a secific model

        }


        return true;

    }
}
