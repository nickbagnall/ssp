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
		
		String message = null;
		
		//Access the presenter data file and create a list of presenters
		String dataPath = this.getServletContext().getRealPath("/sspdata");
		String dataFile = dataPath + "/ssp.dat";
		String tempFile = dataPath + "/ssptemp.dat";
		PresenterList shortList = new PresenterList(); 
		shortList.setPresenters(FileRW.readPresenterFile(dataFile));
		
		//Determine the action
		String action = request.getParameter("submit");
		
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
			if(newPresenter.isValid()) {
				shortList.addPresenter(newPresenter);
				int creRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
				if (creRC == 0) {
					message = firstName + " " + lastName + ", " + email + " has been successfully created";					
				} else {
					message = "Create Failed RC="+creRC;
				}
			} else {
				message = "The presenter details were not valid";
			}
		}
		
		SkySportPresenter temp = null;
		if (action.equals("update")) {
			Iterator<SkySportPresenter> i = shortList.getPresenters().iterator();
			while (i.hasNext()) {
				temp = (SkySportPresenter) i.next();
				if (temp.getId().equals(idNumber)) {
					message = temp.getFirstName() + " " + temp.getLastName() + ", " + temp.getEmail();
					message += " has been updated to " + firstName + " " + lastName + ", " + email;
					temp.setFirstName(firstName);
					temp.setLastName(lastName);
					temp.setEmail(email);
					temp.validate();
					
					break;
				}
			}
			if (temp.isValid()) {
				int updRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
				if (updRC != 0) {
					message = "Update Failed RC="+updRC;
				}
			} else {
				message = "The presenter details were not valid";
			}

		}

		if (action.equals("delete")) {
			Iterator<SkySportPresenter> j = shortList.getPresenters().iterator();
			while (j.hasNext()) {
				temp = j.next();
				if (temp.getId().equals(idNumber)) {
					j.remove();
					break;
				}
			}
			int delRC = FileRW.writePresenterFile(dataFile, tempFile, shortList.getPresenters());
			if (delRC == 0) {
				message = firstName + " " + lastName + " has been deleted";
			} else {
				message = "Delete Failed RC="+delRC;
			}
		}

		request.setAttribute("message", message);
		
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response); 
		//super.doPost(request, response);
	}

}
