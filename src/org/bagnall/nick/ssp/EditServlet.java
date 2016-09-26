package org.bagnall.nick.ssp;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Possible entry route
 * action : presenters.jsp : update(n) where n is the row to update
 * 
 * @author Nick Bagnall
 *
 */
public class EditServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Start of Edit Servlet");
		
		//Retrieve Presenter List from session
		PresenterList shortList = (PresenterList)request.getSession(true).getAttribute("presenterList");
		
		//Determine the action and row number to process
		String action = request.getParameter("submit");
		//System.out.println(action);
		action = action.substring(6);
		//System.out.println(action);
		int item = Integer.parseInt(action);
	
		Iterator<SkySportPresenter> i = shortList.getPresenters().iterator();
		SkySportPresenter tempPresenter = null;
		int cnt = 0;

		while (i.hasNext() && cnt <= item) {
			tempPresenter = i.next();
			cnt++;
		}
	
		String idNumber = tempPresenter.getId();
		String firstName = tempPresenter.getFirstName();
		String lastName = tempPresenter.getLastName();
		String email = tempPresenter.getEmail();
		
		SkySportPresenter newPresenter = new SkySportPresenter(idNumber, firstName, lastName, email);

		request.setAttribute("presenter", newPresenter);
		getServletContext().getRequestDispatcher("/Edit.jsp").forward(request, response);
	}

}
