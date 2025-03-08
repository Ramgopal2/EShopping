package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	//Cart_Id, Customer_Id, Product_Id
	private int id;
	private int customerId;
	private int productId;
	
}
