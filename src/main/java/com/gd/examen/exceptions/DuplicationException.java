package com.gd.examen.exceptions;

public class DuplicationException extends Exception{
	static final long serialVersionUID = -3387516993124229948L;
	public DuplicationException(Exception e,String msg){
		super(msg, e);
	}
	public DuplicationException(String msg){
		super(msg);
	}
}
