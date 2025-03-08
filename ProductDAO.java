package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.ProductDetails;

public interface ProductDAO {

	public int getProductDetails(ProductDetails productDetails);
	public List<ProductDetails> getAllProductDetails();
	public  int deleteProducts(int id);
	public int updateProductDetails(double price, double discount, int quantity, int id);
	
}
