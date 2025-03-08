package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshopping.model.AdminDetails;

public class AdminDAOImpl implements AdminDAO
{

	@Override
	public boolean getAdminDetails(String emailid, String password)
	{
		 String url="jdbc:mysql://localhost:3306/teca63e_shopping?user=root&password=12345";
		 
		  String select="select * from admin where admin_emailid=? and admin_password=?";
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement	=connection.prepareStatement(select);
		preparedStatement.setString(1, emailid);
		preparedStatement.setString(2, password);
		ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()) 
			{
				while(resultSet.next())
				{
					AdminDetails adminDetails =new AdminDetails();
					adminDetails.setAdminemailid(resultSet.getString("admin_emailid"));
					adminDetails.setPassword(resultSet.getString("admin_password"));
				}
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
