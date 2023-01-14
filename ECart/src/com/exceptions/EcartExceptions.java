package com.exceptions;

import com.ecommerce.exception.EcartExceptions;
import com.ecommerce.exception.RuntimeException;
import com.ecommerce.exception.String;
import com.ecommerce.exception.User_Already_Present;
import com.ecommerce.exception.enum;

public class EcartExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public enum ExceptionType {
		User_Already_Present,
        Email_Not_Found,
        Password_Incorrect,
        Email_Not_Verified,
        Unauthorised_User,
        Product_Not_Found,
        Internal_Error,

    }
	public EcartExceptions.ExceptionType type;
	
	public EcartExceptions(String msg, EcartExceptions.ExceptionType type) {
		super(msg);
		this.type=type;
	}
	
	public EcartExceptions(String msg) {
		super(msg);
	}
}
