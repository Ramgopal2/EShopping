package com.eshopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.AdminDAO;
import com.eshopping.DAO.AdminDAOImpl;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String emailid=request.getParameter("emailid");
		String password=request.getParameter("password");
	AdminDAO adminDAO = new AdminDAOImpl();
		try
		{
			if(adminDAO.getAdminDetails(emailid, password)==true) 
			{
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("AdminOperations.html");
				requestDispatcher.forward(request, response);
				
			}
			else {
				
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("AdminLogin.html");
					requestDispatcher.include(request, response);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}