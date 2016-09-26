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

	// private PresenterList shortList = null;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Start of Presenters Servlet");

		PresenterList shortList = (PresenterList) request.getSession(true).getAttribute("presenterList");
		
		String dataPath = this.getServletContext().getRealPath("/sspdata");
		String dataFile = dataPath + "/ssp.dat";
		
		//System.out.println(dataPath);
		//System.out.println(dataFile);
		//System.out.println(tempFile);
		
		if (shortList == null) {
			// If there is no list in the session then this is the first time through
			// Hence read the presenters file and add the presenters to the session
			System.out.println("No Presenter List in Session - reading file");
			shortList = new PresenterList(); 
			shortList.setPresenters(FileRW.readPresenterFile(dataFile));
			request.getSession().setAttribute("presenterList", shortList);
		} 
		
		//At this point we have the presenter list and can display the page
 
		getServletContext().getRequestDispatcher("/Presenters.jsp").forward(request, response);

	}

}
