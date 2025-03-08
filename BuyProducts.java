package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.ProductDetails;

@WebServlet("/buyproducts")
public class BuyProducts extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String tempproductId = request.getParameter("productid");
		String tempcustomerId = request.getParameter("customerid");
		int productId = Integer.parseInt(tempproductId);
		int customerId = Integer.parseInt(tempcustomerId);
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		System.out.println("customer id :"+customerId +" product id :"+productId);
		ProductDAO productDAO = new ProductDAOImpl();
		if(productDAO.buyproducts(productId,quantity)>0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Buy.jsp");
			dispatcher.forward(request, response);
		}
		else
		{

			RequestDispatcher dispatcher = request.getRequestDispatcher("Buy.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
