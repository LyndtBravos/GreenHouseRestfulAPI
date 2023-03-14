package com.voxtelecom;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement(name="client")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	
	private String name;
	private String surname;
	
	public Client() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
}