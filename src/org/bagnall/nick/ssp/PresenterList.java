package org.bagnall.nick.ssp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PresenterList {
	private List<SkySportPresenter> presenters = new ArrayList<SkySportPresenter>();

	/**
	 * blank constructor
	 */
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
		//List<SkySportPresenter> skySportPresenters = this.getPresenters();
	}
	
	/**
	 * Find the next available unique ID
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
