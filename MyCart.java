package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImpl;
import com.eshopping.model.Cart;
@WebServlet("/mycart")
public class MyCart extends HttpServlet{

	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String tempProductId = request.getParameter("productid");
	    String temCustomerId = request.getParameter("customerid");
	    int productId = Integer.parseInt(tempProductId);
	    int customerId = Integer.parseInt(temCustomerId);

	    Cart cart = new Cart();
	    cart.setCustomerId(customerId);
	    cart.setProductId(productId);

	    CartDAO cartDAO = new CartDAOImpl();
	    HttpSession session = request.getSession();
	    List<Cart> cartDetails = cartDAO.getCartDetails(customerId);

	    System.out.println("cart details  "+ cartDetails);
	    if (cartDetails != null && !cartDetails.isEmpty()) {
	        session.setAttribute("cart", cartDetails); // Update session with cart details
	        request.setAttribute("cart", cartDetails);
	    } else {
	        session.setAttribute("cart", null); // Clear session cart if empty
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCartDetails.jsp");
	    dispatcher.forward(request, response);
	}

}
