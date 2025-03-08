package com.eshopping.model;

import java.sql.Date;

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
public class OderDetails {
	//Order_Id, Customer_Id, Product_Id, Purchase_Quantity, Purchase_Date, Total_Purchase_Price
	private int orderId;
	private int customerId;
	private int productId;
	private int purchaseQuantity;
	private Date purchaseDate;
	private double totalpurchasePrice;
}
