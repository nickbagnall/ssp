package org.bagnall.nick.ssp;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bagnall.nick.fileCRUD.FileRW;

/**
 * Possible entry route
 * action : presenters.jsp : create
 *          edit.jsp       : update, delete
 * 
 * 
 * @author Nick Bagnall
 *
 */
public class ConfirmServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Start of Confirm Servlet");
		
		String success = "false";
		String dataPath = this.getServletContext().getRealPath("/sspdata");
		String dataFile = dataPath + "/ssp.dat";
		String tempFile = dataPath + "/ssp.tmp";
		//Retrieve Presenter List from session
		PresenterList shortList = (PresenterList)request.getSession(true).getAttribute("presenterList");
		
		//Determine the action
		String action = request.getParameter("submit");
		System.out.println(action);
		
		//There is no id number on a create request
		String idNumber = "";
		if (!action.equals("create")) {
			idNumber = request.getParameter("id");			
		}
		//Gather the details from the form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		
		if (action.equals("create")) {
			String nextId = shortList.nextId();
			SkySportPresenter newPresenter = new SkySportPresenter(nextId, firstName, lastName, email);
			shortList.addPresenter(newPresenter);
			int creRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
			if (creRC == 0) {
				success = "true";
			} else {
				System.out.println("Create Failed RC="+creRC);
			}
		}
		
		SkySportPresenter temp = null;
		if (action.equals("update")) {
			Iterator<SkySportPresenter> i = shortList.getPresenters().iterator();
			while (i.hasNext()) {
				temp = (SkySportPresenter) i.next();
				if (temp.getId().equals(idNumber)) {
					temp.setFirstName(firstName);
					temp.setLastName(lastName);
					temp.setEmail(email);
					break;
				}
			}
			int updRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
			if (updRC == 0) {
				success = "true";
			} else {
				System.out.println("Update Failed RC="+updRC);
			}

		}

		if (action.equals("delete")) {
			Iterator<SkySportPresenter> j = shortList.getPresenters().iterator();
			while (j.hasNext()) {
				temp = j.next();
				System.out.println("lst object id =  "+ temp.getId());
				System.out.println("matching against " + idNumber);
				if (temp.getId().equals(idNumber)) {
					j.remove();
					break;
				}
			}
			int delRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
			if (delRC == 0) {
				success = "true";
			} else {
				System.out.println("Delete Failed RC="+delRC);
			}
		}


		request.setAttribute("success", success);
		
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response); 
		//super.doPost(request, response);
	}

}
