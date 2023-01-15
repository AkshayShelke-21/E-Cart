package com.exceptions;

import com.exceptions.EcartExceptions;;


//Customised Exceptions for the platform.
public class EcartExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public enum ExceptionType {
		User_Already_Present,
        User_Not_Found,
        Password_Incorrect,
        Username_Not_Found,
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
