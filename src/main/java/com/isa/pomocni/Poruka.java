package com.isa.pomocni;

public class Poruka {
	
	private String message;
	
	private Object obj;
	
	public Poruka(){}

	public Poruka(String message, Object obj){
		this.message = message;
		this.obj = obj;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}	
}
