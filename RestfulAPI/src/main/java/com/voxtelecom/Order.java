package com.voxtelecom;

import java.util.Date;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement(name="order")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	
	private String orderDate;
	private String collectionDate;
	private int doughnuts;
	private String results;
	private int clientID;
	
	public Order(){
		
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	public int getDoughnuts() {
		return doughnuts;
	}

	public void setDoughnuts(int doughnuts) {
		this.doughnuts = doughnuts;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Order(String orderDate, String  collectionDate, int doughnuts, String results, int clientID) {
		super();
		this.orderDate = orderDate;
		this.collectionDate = collectionDate;
		this.doughnuts = doughnuts;
		this.results = results;
		this.clientID = clientID;
	}
}