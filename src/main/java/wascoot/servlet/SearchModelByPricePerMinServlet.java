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

import wascoot.database.SearchModelByPricePerMinDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.resource.Model;

/**
 * Searches employees by their salary.
 * 
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class SearchModelByPricePerMinServlet extends AbstractDatabaseServlet {

	/**
	 * Searches model by their salary.
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
		LogContext.setAction(Actions.SEARCH_MODEL_BY_PRICE_PER_MIN);

		// request parameter
		//int salary = -1;
		double pricePerMin = -1;

		// model
		List<Model> el = null;
		Message m = null;

		try {

			// retrieves the request parameter
			pricePerMin = Double.parseDouble(req.getParameter("price_per_min"));

			// creates a new object for accessing the database and searching the models
			el = new SearchModelByPricePerMinDAO(getConnection(), pricePerMin).access().getOutputParam();

			m = new Message("Models successfully searched.");

			LOGGER.info("Models successfully searched by salary %d.", pricePerMin);

		} catch (NumberFormatException ex) {
			m = new Message("Cannot search for models. Invalid input parameters: price per min must be double.", "E100",
					ex.getMessage());

			LOGGER.error("Cannot search for models. Invalid input parameters: price per min must be double.", ex);
		} catch (SQLException ex) {
			m = new Message("Cannot search for models: unexpected error while accessing the database.", "E200",
					ex.getMessage());

			LOGGER.error("Cannot search for models: unexpected error while accessing the database.", ex);
		}


		try {
			// stores the employee list and the message as a request attribute
			req.setAttribute("searchedModel", el);
			req.setAttribute("message", m);

			// forwards the control to the search-employee-result JSP
			req.getRequestDispatcher("/jsp/search-model-result.jsp").forward(req, res);

		} catch(Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating model %d.", pricePerMin), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}
	}

}
