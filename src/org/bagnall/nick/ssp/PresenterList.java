package org.bagnall.nick.ssp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Object to hold list of presenters to be shown on web page
 * also provides next available id number
 * 
 * @author Nick Bagnall
 *
 */
public class PresenterList {
	
	private List<SkySportPresenter> presenters = new ArrayList<SkySportPresenter>();

	public PresenterList() {
		
	}
	
	public List<SkySportPresenter> getPresenters() {
		return presenters;
	}

	public void setPresenters(List<SkySportPresenter> presenters) {
		this.presenters = presenters;
	}
	
	public void addPresenter(SkySportPresenter presenter)
	{
		presenters.add(presenter);
	}
	
	/**
	 * Find the next available ID
	 * 
	 * @return
	 */
	public String nextId() {
		//Find a unique id and set it on the object
        Iterator<SkySportPresenter> i = presenters.iterator();
        SkySportPresenter temp = null;
        int maxId = 0;
        int tempId = 0;
        while (i.hasNext()) {
               temp = i.next();
               tempId = Integer.parseInt(temp.getId());
               if (tempId > maxId) {
                      maxId = tempId;
               }
        }
        maxId++;
        return String.valueOf(maxId);
	}
}
