package com.neox.inventory.ws.util;

public class Notification {
	
	private String message = null;
	private String title = null;
	
	public Notification() {}
	
	public Notification(String body, String title) {
		this.message = body;
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String body) {
		this.message = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}