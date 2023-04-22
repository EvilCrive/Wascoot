package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * Represents a generic REST resource.
 *
 * @version 1.00
 * @since 1.00
 */
public abstract class RestResource1 {
    /**
     * The HTTP request
     */
    protected final HttpServletRequest req;

    /**
     * The HTTP response
     */
    protected final HttpServletResponse res;

    /**
     * The connection to the database
     */
    protected final Connection con;

    /**
     * Creates a new REST resource.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    protected RestResource1(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        this.req = req;
        this.res = res;
        this.con = con;
    }
}
