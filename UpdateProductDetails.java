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
@WebServlet("/updateproductdetails")
public class UpdateProductDetails extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 double price = Double.parseDouble(request.getParameter("price"));
		 double discount = Double.parseDouble(request.getParameter("discount"));
		 int quantity = Integer.parseInt(request.getParameter("quantity"));
		 int id = Integer.parseInt(request.getParameter("productId"));
	       
		ProductDAO productDAO = new ProductDAOImpl();
        ProductDetails productDetails = new ProductDetails();
        PrintWriter writer = response.getWriter();
       System.out.println(id);
       if(productDAO.updateProductDetails(price,discount,quantity,id)>0)
       {
    	   
    	   RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllProductDetails.jsp");
           requestDispatcher.include(request, response);
        } else {
        	 RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllProductDetails.jsp");
             requestDispatcher.include(request, response);
             writer.println("Not Deleting");
	}
}
}
