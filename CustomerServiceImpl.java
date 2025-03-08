package com.eshopping.service;

import java.util.List;
import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.exceptionClasses.CustomerException;
import com.eshopping.model.CustomerDetails;

public class CustomerServiceImpl  implements CustomerService{

	CustomerDAO customerDAO = new CustomerDAOImpl();
	@Override
	public int userRegistration(CustomerDetails customerDetails) {
		List<CustomerDetails> allCustomerDetails = customerDAO.getAllCustomerDetails();
		boolean emailmatch = allCustomerDetails.stream().anyMatch((customer -> customer.getEmailid().equalsIgnoreCase(customerDetails.getEmailid())));
		if(emailmatch)	
		{
			throw new CustomerException("Emailid Already Existed");
		}
		boolean mobilenumbermatch = allCustomerDetails.stream().anyMatch((customer -> customer.getMobilenumber()==(customerDetails.getMobilenumber())));
		if(mobilenumbermatch)	
		{
			throw new CustomerException("MobileNumber Already Existed");
		}
		boolean passwordmatch = allCustomerDetails.stream().anyMatch((customer -> customer.getPassword().equalsIgnoreCase(customerDetails.getPassword())));
		if(passwordmatch)	
		{
			throw new CustomerException("Password Already Existed");
		}
		
		int result = customerDAO.insertCustomerDetails(customerDetails);
		return result;
	}

}
