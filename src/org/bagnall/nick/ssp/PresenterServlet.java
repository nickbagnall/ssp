package org.bagnall.nick.ssp;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bagnall.nick.fileCRUD.FileRW;

/**
 * Possible entry route
 * action : index.html   : start 
 * 			edit.jsp     : cancel
 *          confirm.jsp  : complete
 * 
 * 
 * @author Nick Bagnall
 *
 */
public class PresenterServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Access the presenter data file and create a list of presenters
		String dataPath = this.getServletContext().getRealPath("/sspdata");
		String dataFile = dataPath + "/ssp.dat";
		PresenterList shortList = new PresenterList(); 
		shortList.setPresenters(FileRW.readPresenterFile(dataFile));
		request.setAttribute("presenterList", shortList);
		
		//At this point we have the presenter list and can display the page
 		getServletContext().getRequestDispatcher("/Presenters.jsp").forward(request, response);

	}

}
