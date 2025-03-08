package com.eshopping.exceptionClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor

public class CustomerException extends RuntimeException{

	String msg;
	
}
