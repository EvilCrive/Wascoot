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

import wascoot.database.CreateModelDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.resource.Model;


public final class CreateModelServlet extends AbstractDatabaseServlet {

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
		LogContext.setAction(Actions.CREATE_MODEL);

		// request parameters
		/*int badge = -1;
		String surname = null;
		int age = -1;
		int salary = -1;*/
		String name = null;
		String brand = null;
		//LocalTime batteryLife = null;
		String batteryLife = null;
		double pricePerMin = -1;

		// model
		Model e = null;
		Message m = null;

		try {
			// retrieves the request parameters
			name = req.getParameter("name");
			brand = req.getParameter("brand");
			//batteryLife = LocalTime.parse(req.getParameter("battery_life"));
			batteryLife = req.getParameter("battery_life");
			pricePerMin = Double.parseDouble(req.getParameter("price_per_min"));

			// set the badge of the employee as the resource in the log context
			// at this point we know it is a valid integer
			LogContext.setResource(req.getParameter("name"));

			// creates a new employee from the request parameters
			e = new Model(name, brand, Time.valueOf(batteryLife), pricePerMin);

			// creates a new object for accessing the database and stores the employee
			new CreateModelDAO(getConnection(), e).access();

			m = new Message(String.format("Model %s successfully created.", name));

			LOGGER.info("Model %s successfully created in the database.", name);

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot create the model. Invalid input parameters: badge, age, and salary must be integer.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot create the model. Invalid input parameters: badge, age, and salary must be integer.",
					ex);
		} catch (SQLException ex) {
			if ("23505".equals(ex.getSQLState())) {
				m = new Message(String.format("Cannot create the employee: employee %s already exists.", name), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot create the employee: employee %s already exists.", name),
						ex);
			} else {
				m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
			}
		}

		try {
			// stores the employee and the message as a request attribute
			req.setAttribute("newModel", e);
			req.setAttribute("message", m);

			// forwards the control to the create-employee-result JSP
			req.getRequestDispatcher("/jsp/create-model-result.jsp").forward(req, res);
		} catch(Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating model %s.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
