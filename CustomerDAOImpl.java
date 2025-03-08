package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public class CustomerDAOImpl implements CustomerDAO{

	
	public static final String url = "jdbc:mysql://localhost:3306/teca63e_shopping?user=root&password=12345";
	public static final String insert ="insert into customer_details(Name, Emailid, Password, Mobile_Number, Gender, Address)values(?,?,?,?,?,?)";
	public static final String select = "select * from customer_details";
	public static final String select1 = "select * from customer_details where Emailid = ? and Password =?";
	@Override
	public int insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection	=DriverManager.getConnection(url);
		PreparedStatement preparedStatement = connection.prepareStatement(insert);
		preparedStatement.setString(1, customerDetails.getName());
		preparedStatement.setString(2, customerDetails.getEmailid());
		preparedStatement.setString(3, customerDetails.getPassword());
		preparedStatement.setLong(4, customerDetails.getMobilenumber());
		preparedStatement.setString(5, customerDetails.getGender());
		preparedStatement.setString(6, customerDetails.getAddress());

		return preparedStatement.executeUpdate();
		
		} 
		catch (ClassNotFoundException e) 
		{
			return 0;
		} 
		catch (SQLException e) 
		{
			return 0;
		}
	}
	@Override
	public List<CustomerDetails> getAllCustomerDetails() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url);
	        PreparedStatement preparedStatement = connection.prepareStatement(select);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        List<CustomerDetails> list = new ArrayList<>();
	        if (resultSet.isBeforeFirst()) {
	            while (resultSet.next()) {
	                CustomerDetails customerDetails = new CustomerDetails();
	                customerDetails.setEmailid(resultSet.getString("Emailid"));
	                customerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
	                customerDetails.setPassword(resultSet.getString("Password"));
	                customerDetails.setId(resultSet.getInt("Id"));;
	                list.add(customerDetails); 
	            }
	        }

	        System.out.println("List retrieved from DB: " + list);  // Debugging line
	        return list;

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	@Override
	public CustomerDetails login(String emailid, String password) {
	    CustomerDetails customerDetails = null; // Initialize as null

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url);
	        PreparedStatement preparedStatement = connection.prepareStatement(select1);
	        preparedStatement.setString(1, emailid);
	        preparedStatement.setString(2, password);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            customerDetails = new CustomerDetails();
	            customerDetails.setEmailid(resultSet.getString("Emailid"));
	            customerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
	            customerDetails.setPassword(resultSet.getString("Password"));
	            customerDetails.setName(resultSet.getString("Name"));
	            customerDetails.setId(resultSet.getInt("Id"));
	        }
	        return customerDetails; 
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return null;
	    }

	  // Will be null if no match
	}

	

}
