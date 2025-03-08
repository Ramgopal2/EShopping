package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.exceptionClasses.CustomerException;
import com.eshopping.model.CustomerDetails;
import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImpl;
@WebServlet("/customerregistation")
public class CustomerRegistration extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		System.out.println(password);
		String tempphno = request.getParameter("mobileno");
		System.out.println(tempphno+ " is");
		long mobileno = Long.parseLong(tempphno);
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		
		CustomerDetails  customerDetails = new CustomerDetails();
		customerDetails.setName(name);
		customerDetails.setEmailid(emailid);
		customerDetails.setPassword(password);
		customerDetails.setMobilenumber(mobileno);
		customerDetails.setGender(gender);
		customerDetails.setAddress(address);
		
		CustomerDAO  customerDAO = new CustomerDAOImpl();
		PrintWriter writer = response.getWriter();
		try {
		if(customerDAO.insertCustomerDetails(customerDetails)>0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerLogin.html");
			dispatcher.forward(request, response);
			
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerRegistration.html");
			dispatcher.include(request, response);
			writer.println("Customer Registration is failed");
		}
		}
		catch (CustomerException  e) {
			e.getMsg();
		}
	}
}
