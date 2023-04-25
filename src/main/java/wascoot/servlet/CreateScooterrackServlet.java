/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.servlet;

import wascoot.database.CreateScooterrackDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;


public final class CreateScooterrackServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new model into the database.
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
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.INSERT_NEW_SCOOTERRACK);

        Integer id = null;
        Integer totalParkingSpots = null;
        Integer availableParkingSpots = null;
        String postalCode = null;
        String address = null;

        Scooterrack s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            totalParkingSpots = Integer.parseInt(req.getParameter("total_parking_spots"));
            availableParkingSpots = Integer.parseInt(req.getParameter("available_parking_spots"));
            postalCode = req.getParameter("postalcode");
            address = req.getParameter("address");

            LogContext.setResource("new scooterrack");

            s = new Scooterrack(-1, totalParkingSpots, availableParkingSpots, postalCode, address);

            new CreateScooterrackDAO(getConnection(), s).access();

            m = new Message("New scooterrack successfully created.");

            LOGGER.info("New scooterrack successfully created in the database.");

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooterrack. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooterrack. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {
            m = new Message("Cannot create the scooterrack: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the scooterrack: unexpected error while accessing the database.", ex);
        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("newScooterrack", s);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/create-scooterrack-result.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooterrack."), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
