package com.voxtelecom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/display")
public class Endpoint {

	//Querries to run to create database
	/*

	CREATE TABLE `doughnutassessment`.`clients` (
  `ClientsID` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ClientsID`));

  CREATE TABLE `doughnutassessment`.`orders` (
  `idorders` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NULL,
  `collection_date` DATE NULL,
  `doughnuts_ordered` INT NULL,
  `topping_type` VARCHAR(45) NULL,
  `Client_ID` INT NULL,
  PRIMARY KEY (`idorders`),
  UNIQUE INDEX `idorders_UNIQUE` (`idorders` ASC),
  INDEX `ClientID_idx` (`Client_ID` ASC),
  CONSTRAINT `ClientID`
		FOREIGN KEY (`Client_ID`)
		REFERENCES `doughnutassessment`.`clients` (`ClientsID`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);

	CREATE SCHEMA `doughnutassessment` ;

	 */


	@GET
	@Path("/sayHie")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHie() {
		return "Hello World!!!";
	}
	
	@GET
	@Path("/getOrders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders(){
		List<Order> orderList = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "SELECT * FROM orders;";
		
		try {
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String date1 = rs.getString(2);
				String date2 = rs.getString(3);
				int donuts = rs.getInt(4);
				String results = rs.getString(5);
				int clientID = rs.getInt(6);
				
				Order order = new Order(date1, date2, donuts, results, clientID);
				orderList.add(order);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	@GET
	@Path("/getClients")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> getClients(){
		List<Client> clientList = new ArrayList<Client>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "SELECT * FROM clients;";
		
		try {
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString(2);
				String surname = rs.getString(3);
				
				Client client = new Client(name, surname);
				clientList.add(client);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return clientList;
	}
	
	@POST
	@Path("/addClient")
	@Produces(MediaType.APPLICATION_JSON)
	public void addClient(Client client) {
		postClient(client);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addOrder")
	public void addOrder(Order order) {
		newOrder(order);
	}
	
	@PUT
	@Path("/updateClient/{clientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateClient(Client client, @PathParam("clientID") int clientID) {
		updClient(client, clientID);
	}
	
	@PUT
	@Path("/updateOrder/{idOrder}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateOrder(Order order, @PathParam("idOrder") int idOrder) {
		updOrder(order, idOrder);
	}
	
	@DELETE
	@Path("/deleteClient/{clientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteClient(@PathParam("clientID") int clientID) {
		delClient(clientID);
	}
	
	@DELETE
	@Path("/deleteOrder/{idOrder}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteOrder(@PathParam("idOrder") int idOrder) {
		delOrder(idOrder);
	}
	
	public void delOrder(@PathParam("idOrder") int ID) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "DELETE FROM `doughnutassessment`.`orders` WHERE orders.idorders = ?;";
		
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			ps.setInt(1, ID);
			ps.execute();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void delClient(int ID) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "DELETE FROM `doughnutassessment`.`clients` WHERE clients.ClientsID = ?;";
		
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			ps.setInt(1, ID);
			ps.execute();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void updOrder(Order order, int ID) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "UPDATE `doughnutassessment`.`orders` SET `order_date` = ?, `collection_date` = ?, `doughnuts_ordered` = ?, `topping_type` = ?, `ClientID` = ? WHERE `idorders` = ?;";
		
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			ps.setString(1, order.getOrderDate());
			ps.setString(2, order.getCollectionDate());
			ps.setInt(3, order.getDoughnuts());
			ps.setString(4, order.getResults());
			ps.setInt(5, order.getClientID());
			ps.setInt(6, ID);
			ps.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void updClient(Client client, int ID) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "UPDATE `doughnutassessment`.`clients` SET `first_name` = ?, `last_name` = ? WHERE `ClientsID` = ?;";
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			ps.setString(1, client.getName());
			ps.setString(2, client.getSurname());
			ps.setInt(3, ID);
			ps.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void postClient(Client client) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "INSERT INTO `doughnutassessment`.`clients` (`first_name`, `last_name`) VALUES (?,?);";
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(url, root, pass);
			ps = con.prepareStatement(query);
			ps.setString(1, client.getName());
			ps.setString(2, client.getSurname());
			ps.executeUpdate();		
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void newOrder(Order order) {
		Connection con = null;
		PreparedStatement ps = null;
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/doughnutassessment?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
		String root = "root";
		String pass = "psycho";
		String query = "INSERT INTO `doughnutassessment`.`orders` (`order_date`, `collection_date`, `doughnuts_ordered`, `topping_type`, `ClientID`) VALUES (?, ?, ?, ?, ?);";
		int i = 0;
		System.out.println("Hello: " + i);
		try {
			Class.forName(myDriver);

			con = DriverManager.getConnection(url, root, pass);

			ps = con.prepareStatement(query);
			ps.setString(1, order.getOrderDate());
			ps.setString(2, order.getCollectionDate());
			ps.setInt(3, order.getDoughnuts());
			ps.setString(4, order.getResults());
			ps.setInt(5, order.getClientID());
			i = ps.executeUpdate();		
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}