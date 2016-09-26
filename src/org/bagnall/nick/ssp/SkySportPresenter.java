package org.bagnall.nick.ssp;

public class SkySportPresenter {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isValid = false;
	
	public SkySportPresenter() {
		
	}
	
	public SkySportPresenter(String id, String firstName, String lastName, String email)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isValid = validate();
	}

	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	/**
	 * Return the standard short format for this presenter
	 * 
	 * @return
	 */
	public String getShortForm () {
		String shortForm = id+":"+firstName+":"+lastName+":"+email;
		return shortForm;
	}
	
	/**
	 * Split a short format entry and update this object with the values
	 * Simple version no fields can be empty
	 * 
	 * @param presenterData
	 */
	public void parsePresenterString(String presenterData) {
		isValid = false;
		if (presenterData != null) {
			try {

				String[] values = presenterData.split(":");
				id = values[0];
				firstName = values[1];
				lastName = values[2];
				email = values[3];
				isValid = validate(); 
			}
			catch (Exception e ) {
				//Ignore because isValid already = false
			}
		}
	}
	
	public boolean validate() {
		boolean valid = true;
		if (!check(firstName, false)) {
			valid = false;
		}
		if (!check(lastName, false)) {
			valid = false;
		}
		if (!check(email, false)) {
			valid = false;
		}
		System.out.println("Validate returns : "+ valid);
		return valid;
	}
	
	private boolean check(String name, boolean canBeZero){
		if (canBeZero) {
			if (name == null || name == "") {
				return true;
			}
		} else {
			if (name == null || name == "") {
				return false;
			}
		}
		if (name.indexOf('<') > 0 ) {
			return false;
		}
		if (name.indexOf('>') > 0 ) {
			return false;
		}
		if (name.indexOf('&') > 0 ) {
			return false;
		}
		if (name.indexOf('"') > 0 ) {
			return false;
		}
		if (name.indexOf('\'') > 0 ) {
			return false;
		}
		if (name.indexOf('/') > 0 ) {
			return false;
		}
		return true;
		
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;		
	}
	
	public boolean isValid() {
		return isValid;
	}
}
