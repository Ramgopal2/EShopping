package com.eshopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImpl;
import com.eshopping.model.Cart;
@WebServlet("/addtocart")
public class AddToCart extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tempProId = request.getParameter("productid");
		int productId = 	Integer.parseInt(tempProId);
		String tempCustId = request.getParameter("customerid");
		int customerId = Integer.parseInt(tempCustId);
		
		Cart cart = new Cart();
		cart.setCustomerId(customerId);
		cart.setProductId(productId);
		CartDAO cartDAO = new CartDAOImpl();
		if(cartDAO.insertCartDetails(cart)>0)
		{
			int count = cartDAO.getCustomerCartCount(customerId);
			request.getSession().setAttribute("cartcount", count);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}
}
