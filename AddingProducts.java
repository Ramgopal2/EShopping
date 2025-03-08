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
@WebServlet("/addingproducts")
public class AddingProducts extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String  productName  =  request.getParameter("productname");
		String  productprice =  request.getParameter("price");
		double price = Double.parseDouble(productprice);
		String productdiscount =  request.getParameter("quantity");
		double discount= Double.parseDouble(productdiscount);
		String  productquantity =  request.getParameter("discount");
		int quantity = Integer.parseInt(productdiscount);
		
		ProductDetails productDetails = new ProductDetails();
		productDetails.setCategory(category);
		productDetails.setBrand(brand);
		productDetails.setName(productName);
		productDetails.setPrice(price);
		productDetails.setQuantity(quantity);
		productDetails.setDiscount(discount);
		//System.out.println(productDetails);
		ProductDAO productDAO = new ProductDAOImpl();
		PrintWriter printWriter = response.getWriter();
		if(productDAO.getProductDetails(productDetails)>0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			dispatcher.forward(request, response);
		printWriter.println("products added");	
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			dispatcher.forward(request, response);
		printWriter.println("products not added");	
		}
	}
	
}
