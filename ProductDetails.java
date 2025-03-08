package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Setter
@Getter
@ToString*/
@Data   // instead of mentioning @Setter, @Getters, and @ToString we use only single annotation called @Data.
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
//Id, Name, Brand, Price, Discount, Category, Quantity
	
	private int id;
	private String name;
	private String brand;
	private double price;
	private double discount;
	private String category;
	private int quantity;
}
