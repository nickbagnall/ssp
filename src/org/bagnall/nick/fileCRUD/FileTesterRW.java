package org.bagnall.nick.fileCRUD;

import java.util.Iterator;
import java.util.List;

import org.bagnall.nick.fileCRUD.FileRW;
import org.bagnall.nick.ssp.Presenter;


public class FileTesterRW {

	public static void main(String[] args) {

		//Test file reader
		List<Presenter> myList = FileRW.readPresenterFile("test2.dat");
		
		//Look at the list and check it's ok
		Iterator<Presenter> i = myList.iterator();
		Presenter temp = null;
		while(i.hasNext()) {
			temp = i.next();
			System.out.println(temp.getShortForm());
		}
		
		//Add a couple of presenters to the list
		temp = new Presenter("Nick", "Bagnall", "Nick.Bagnall@example.org");
		myList.add(temp);
		temp = new Presenter("Peter", "Rabbit", "PR@example.org");
		myList.add(temp);
		
		
		//Write the amendments
		int returnCode = FileRW.writePresenterFile("test2.dat", "temp2.dat", myList);
		System.out.println("Return code from write file : " + returnCode);
		
		
	}

}
