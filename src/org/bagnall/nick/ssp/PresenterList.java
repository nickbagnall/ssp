package org.bagnall.nick.ssp;

import java.util.ArrayList;
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
	
	public int getSize() {
		return presenters.size();
	}
}
