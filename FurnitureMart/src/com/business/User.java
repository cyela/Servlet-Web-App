package com.business;

import java.io.Serializable;
import java.sql.Blob;

public class User implements Serializable {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String salt;
	private String gender;
	private String phonenumber;
	private String DOB;
	private Blob image;

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public User(String firstName, String lastName, String email, String password, String salt, String gender,
			String phonenumber, String dOB, Blob image) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.gender = gender;
		this.phonenumber = phonenumber;
		DOB = dOB;
		this.image = image;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", salt=" + salt + ", gender=" + gender + ", phonenumber=" + phonenumber + ", DOB=" + DOB + "]";
	}

}
