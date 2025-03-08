package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.Cart;
import com.eshopping.model.ProductDetails;

import java.sql.ResultSetMetaData;

public class CartDAOImpl implements CartDAO{


	public static final String url = "jdbc:mysql://localhost:3306/teca63e_shopping?user=root&password=12345";
	public static final String count_Of_Customer_Id="select count(*) from cart where Customer_Id=?";
	public static final String  insert_cart= "insert into cart(Customer_Id, Product_Id) values (?,?)";
	public static final String selectAll = "select * from cart where Customer_Id =?";
	@Override
	public int getCustomerCartCount(int customerId) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection  connection =DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(count_Of_Customer_Id);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				System.out.println("it is working");
				return resultSet.getInt(1);
				
			}
			else
			{
				System.out.println("it is  not working");

				return 0;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int  insertCartDetails(Cart cart) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection  connection =DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_cart);
			preparedStatement.setInt(1, cart.getCustomerId());
			preparedStatement.setInt(2,cart.getProductId());
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Cart> getCartDetails(int customerid) {
		
		    List<Cart> list = new ArrayList<Cart>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url);
	        PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
	        preparedStatement.setInt(1, customerid);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) { // Use a loop to fetch all rows
	            Cart cart = new Cart();
	            cart.setCustomerId(resultSet.getInt("Customer_Id"));
	            cart.setProductId(resultSet.getInt("Product_Id"));
	            list.add(cart);
	        }
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
		
		
	}

}
