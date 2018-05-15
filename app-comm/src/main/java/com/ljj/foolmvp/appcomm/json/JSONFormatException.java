package com.ljj.foolmvp.appcomm.json;


/**
 * Created by lijunjie on 2017/12/21.
 */
public class JSONFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411745024910244643L;
	
	public JSONFormatException(){
		super();
	}

	public JSONFormatException(String message){
		super(message);
	}
	
	public JSONFormatException(String message,Throwable cause){
		super(message,cause);
	}

}
