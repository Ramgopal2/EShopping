package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.CustomerDetails;

public interface CustomerDAO {

	public int insertCustomerDetails(CustomerDetails customerDetails);
	public List<CustomerDetails> getAllCustomerDetails();
	public  CustomerDetails login(String emailid, String password);
}
