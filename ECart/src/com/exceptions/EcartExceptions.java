package com.exceptions;

import com.ecommerce.exception.EcommerceException;
import com.ecommerce.exception.RuntimeException;
import com.ecommerce.exception.String;
import com.ecommerce.exception.USER_ALREADY_PRESENT;
import com.ecommerce.exception.enum;

public class EcartExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public enum ExceptionType {
        USER_ALREADY_PRESENT,
        EMAIL_NOT_FOUND,
        PASSWORD_INCORRECT,
        EMAIL_NOT_VERIFIED,
        UNAUTHORISED_USER,
        PRODUCT_NOT_FOUND,
        INTERNAL_ERROR,
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
