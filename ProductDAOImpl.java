package com.eshopping.DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.ProductDetails;

public class ProductDAOImpl implements ProductDAO{

	String insert = "insert into product_details(Name, Brand, Price, Discount, Category, Quantity) values(?,?,?,?,?,?)";
	String url = "jdbc:mysql://localhost:3306/teca63e_shopping?user=root&password=12345";
	String select ="select * from  product_details";
	String delete = "delete from product_details where id =?";
	String update = "update product_details set Price = ?, Discount = ?, Quantity = ? where id = ?";
	String buy = "update product_details set  Quantity -? where id =?";
	
	@Override
	public int getProductDetails(ProductDetails productDetails) {
		//ProductDetails productDetails = new ProductDetails();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, productDetails.getName());
			preparedStatement.setString(2, productDetails.getBrand());
			preparedStatement.setDouble(3, productDetails.getPrice());
			preparedStatement.setDouble(4, productDetails.getDiscount());
			preparedStatement.setString(5, productDetails.getCategory());
			preparedStatement.setInt(6, productDetails.getQuantity());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ProductDetails> getAllProductDetails() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(select);
		ResultSet resultSet=	preparedStatement.executeQuery();
		List<ProductDetails> allProductDetails = new ArrayList<ProductDetails>();
		ProductDAO productDAO=new ProductDAOImpl();
		if(resultSet.isBeforeFirst())
		{
			while(resultSet.next())
			{
				//Id, Name, Brand, Price, Discount, Category, Quantity
				ProductDetails productDetails = new  ProductDetails();
				productDetails.setId(resultSet.getInt("Id"));
				productDetails.setName(resultSet.getString("Name"));
				productDetails.setBrand(resultSet.getString("Brand"));
				productDetails.setPrice(resultSet.getDouble("Price"));
				productDetails.setDiscount(resultSet.getDouble("Discount"));
				productDetails.setCategory(resultSet.getString("Category"));
				productDetails.setQuantity(resultSet.getInt("Quantity"));
				allProductDetails.add(productDetails);	
			}
		}
		return allProductDetails;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public int deleteProducts(int id) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(delete);
			preparedStatement.setInt(1,id);
			System.out.println("Deleting product with ID: " + id);

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
	public int updateProductDetails(double price, double discount, int quantity, int id) {
		
		
			 try {
			        Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection connection = DriverManager.getConnection(url);
			        
			        // Corrected: Use the update query here
			        PreparedStatement preparedStatement = connection.prepareStatement(update);  // Use 'update' instead of 'delete'
			        
			        preparedStatement.setDouble(1, price);
			        preparedStatement.setDouble(2, discount);
			        preparedStatement.setInt(3, quantity);
			        preparedStatement.setInt(4, id);

			        System.out.println("Updating product with price: " + price);
			        System.out.println("Updating product with discount: " + discount);
			        System.out.println("Updating product with quantity: " + quantity);
			        System.out.println("Updating product with ID: " + id);

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
	public int buyproducts(int id,int quantity) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement =connection.prepareStatement(buy);
			 preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, id);
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
	
}
