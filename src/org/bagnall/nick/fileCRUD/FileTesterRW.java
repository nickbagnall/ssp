package org.bagnall.nick.fileCRUD;

import java.util.Iterator;
import java.util.List;

import org.bagnall.nick.fileCRUD.FileRW;
import org.bagnall.nick.ssp.SkySportPresenter;


public class FileTesterRW {

	public static void main(String[] args) {

		//Test file reader
		List<SkySportPresenter> myList = FileRW.readPresenterFile("test2.dat");
		
		//Look at the list and check it's ok
		System.out.println("This is the list we loaded");
		Iterator<SkySportPresenter> i = myList.iterator();
		SkySportPresenter temp = null;
		while(i.hasNext()) {
			temp = i.next();
			System.out.println(temp.getShortForm());
		}
		
		//Add a couple of presenters to the list
		temp = new SkySportPresenter("1", "Nick", "Bagnall", "Nick.Bagnall@example.org");
		myList.add(temp);
		temp = new SkySportPresenter("2", "Peter", "Rabbit", "PR@example.org");
		myList.add(temp);
		
		System.out.println("Writing...");
		//Write the amendments
		int returnCode = FileRW.writePresenterFile("test2.dat", "temp2.dat", myList);
		System.out.println("Return code from write file : " + returnCode);
		
		
	}

}