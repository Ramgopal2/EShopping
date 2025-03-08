package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.Cart;
import com.eshopping.model.ProductDetails;

public interface CartDAO {

	public int getCustomerCartCount(int customerId);
	public int insertCartDetails(Cart cart);
	List<Cart> getCartDetails(int customerid);
}
