package com.eshopping.model;

import java.sql.Date;
import java.sql.Time;

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
public class PaymentDetails {
//Payment_Id, Ammount, Payment_Date, Payment_Time, Payment_Type, Customer_Id, Product_Id
	private int paymentId;
	private double ammount;
	private Date paymentDate;
	private Time paymentTime;
	private String paymentType;
	private int customerId;
	private int productId;
}
