package org.br.curso.actions;

import static com.br.inmetrics.frm.helpers.PropertyHelper.getProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.br.inmetrics.frm.helpers.LoggerHelper;

public class DBActions {
	
	public class CustomerTO {
		
		public CustomerTO(int id, 
						  String companyName, 
						  String contactName, 
						  String address, 
						  String city, 
						  String state) {
			
			this.id = id;
			this.companyName = companyName;
			this.contactName = contactName;
			this.address = address;
			this.city = city;
			this.state = state;
		}
		
		public int id;
		public String companyName;
		public String contactName;
		public String address;
		public String city;
		public String state;
		
	}
	
	private Connection connection;
	private int lastIdCreated;
	private static DBActions instance;
	
	private LoggerHelper logger = new LoggerHelper(DBActions.class);
	
	private DBActions() {
	
		if(instance == null)
			instance = this;
	}
	
	public static DBActions createOrInstance() {
		if(instance == null)
			new DBActions();
		
		return instance;
	}
	
	public int lastIdCreated() {
		return lastIdCreated;
	}
	
	public boolean isConnected() {
		return connection != null;
	}
	
	public void initializeConnecion() throws SQLException {
		connection = DriverManager.getConnection(getProperty("db.connections.string"));
	}
	
	public ArrayList<CustomerTO> getCustomers() throws SQLException {
		
		ArrayList<CustomerTO> results = new ArrayList<DBActions.CustomerTO>();
		
		String selectStmt = "SELECT * FROM Customers";		
		PreparedStatement stmt = connection.prepareStatement(selectStmt);
		
		logger.info("SQL STMT:" + selectStmt);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			results.add(new CustomerTO(rs.getInt("CustomerId"), 
										rs.getString("CompanyName"), 
										rs.getString("ContactName"), 
										rs.getString("Address"),
										rs.getString("City"),
										rs.getString("State")));
		}
		
		return results;
		
	}
	
	public CustomerTO getCustomer(int id) throws SQLException {
		
		String selectStmt = "SELECT * FROM Customers WHERE CustomerID=?"; 
		
		PreparedStatement stmt = connection.prepareStatement(selectStmt);
		stmt.setInt(1, id);
		
		logger.info("SQL STMT:" + selectStmt);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			CustomerTO result = new CustomerTO(rs.getInt("CustomerId"), 
											   rs.getString("CompanyName"), 
											   rs.getString("ContactName"), 
											   rs.getString("Address"),
											   rs.getString("City"),
											   rs.getString("State"));
			
			return result;
		}
		
		return null;
	}
	
	public int insertCustomer(String companyName, 
							  String contactName,
							  String address,
							  String city, 
							  String state) throws SQLException{
		
		lastIdCreated = -1;
		
		String insertStmt = "INSERT INTO Customers " + 
							"(CompanyName, ContactName, Address, City, State) " + 
							" VALUES (?, ?, ?, ?, ?) ";
		
		PreparedStatement stmt = connection.prepareStatement(insertStmt);
		stmt.setString(1, companyName);
		stmt.setString(2, contactName);
		stmt.setString(3, address);
		stmt.setString(4, city);
		stmt.setString(5, state);
		
		logger.info("SQL STMT:" + insertStmt);
		stmt.executeUpdate();
		
		lastIdCreated = getLastIdCreated();
						
		return lastIdCreated;
	}
	
	public int getLastIdCreated() throws SQLException {
		
		String selectStmt = "SELECT seq FROM sqlite_sequence WHERE Name='Customers'";
		Statement stmt = connection.createStatement();
		
		logger.info("SQL STMT:" + selectStmt);
		ResultSet result = stmt.executeQuery(selectStmt);
		
		if(result.next())
			return result.getInt("seq");
		
		return -1;
				
	}
	
	public int updateCustomer(int id,
							  String companyName, 
							  String contactName,
							  String address,
							  String city, 
							  String state) throws SQLException{
			 
		String updateStmt = "UPDATE Customers SET " + 
							"CompanyName=?, " +
							"ContactName=?, " +
							"Address=?, " + 
							"City=?, " + 
							"State=? " +
							"WHERE " + 
							"CustomerID=?";
		
		PreparedStatement stmt = connection.prepareStatement(updateStmt);
		stmt.setString(1, companyName);
		stmt.setString(2, contactName);
		stmt.setString(3, address);
		stmt.setString(4, city);
		stmt.setString(5, state);
		stmt.setInt(6, id);
		
		logger.info("SQL STMT:" + updateStmt);
		
		return stmt.executeUpdate();
									
	}
	
	public int deleteCustomer(int id) throws SQLException {
		
		String deleteStmt = "DELETE FROM Customers WHERE CustomerID=?";
		
		PreparedStatement stmt = connection.prepareStatement(deleteStmt);
		stmt.setInt(1, id);
		
		logger.info("SQL STMT:" + deleteStmt);
		
		return stmt.executeUpdate();
	}

}
