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
import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.CustomerDetails;
import com.eshopping.model.ProductDetails;
@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String emailid = request.getParameter("emailid");
	    String password = request.getParameter("password");
	    
	    
		CartDAO cartDAO = new CartDAOImpl();
	    CustomerDAO customerDAO = new CustomerDAOImpl(); 
	    ProductDAO productDAO = new ProductDAOImpl();
	    
	    
	    CustomerDetails customerDetails = customerDAO.login(emailid, password);
		List<ProductDetails> allProductDetails  = productDAO.getAllProductDetails();
		
		int id = customerDetails.getId();
		System.out.println("Customer iD :"+id);
		int count = cartDAO.getCustomerCartCount(id);
		System.out.println("Cart Count :"+ count);
		
		System.out.println(customerDetails);
	    PrintWriter writer = response.getWriter();
	    HttpSession session = request.getSession();
	    System.out.println(allProductDetails);
	    if (customerDetails != null) {
	        // Login successful
	    	session.setAttribute("customerdetail", customerDetails);
	    	session.setAttribute("allProductDetails", allProductDetails);
			session.setAttribute("cartcount", count);
			
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
	        requestDispatcher.forward(request, response);
	    } else {
	        // Login failed
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerLogin.html");
	        requestDispatcher.include(request, response);
	        writer.println("<p style='color:red; text-align:center;'>Invalid credentials. Please try again.</p>");
	    }
	}
}
