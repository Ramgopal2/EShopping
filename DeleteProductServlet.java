package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.ProductDetails;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAOImpl();
        ProductDetails productDetails = new ProductDetails();
        PrintWriter writer = response.getWriter();
       int id = Integer.parseInt(request.getParameter("productId"));
       System.out.println(id);
        if (productDAO.deleteProducts(id)>0) {
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllProductDetails.jsp");
           requestDispatcher.include(request, response);
        } else {
        	 RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllProductDetails.jsp");
             requestDispatcher.include(request, response);
             writer.println("Not Deleting");
        }
    }
}
