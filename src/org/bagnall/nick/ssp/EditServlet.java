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
 * action : presenters.jsp : updatennn where nnn is the id of the record we need to edit
 * 
 * @author Nick Bagnall
 *
 */
public class EditServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Access the presenter data file and create a list of presenters
		String dataPath = this.getServletContext().getRealPath("/sspdata");
		String dataFile = dataPath + "/ssp.dat";
		PresenterList shortList = new PresenterList(); 
		shortList.setPresenters(FileRW.readPresenterFile(dataFile));
		
		//From the action determine the record id to process
		String action = request.getParameter("submit");
		String id = action.substring(6);
		
		String message = null;
		
		//Get the presenter details from the id passed in
		SkySportPresenter tempPresenter = null;
		Iterator<SkySportPresenter> i = shortList.getPresenters().iterator();
		while (i.hasNext()) {
			tempPresenter = i.next();
			if (tempPresenter.getId().equals(id)) {
				break;
			}
			tempPresenter = null;
		}
		
		if(tempPresenter == null) {
			message = "The specified Presenter could not be found";
		}
		request.setAttribute("presenter", tempPresenter);
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/Edit.jsp").forward(request, response);
	}

}
