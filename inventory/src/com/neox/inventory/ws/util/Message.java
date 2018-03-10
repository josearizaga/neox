package com.neox.inventory.ws.util;

import java.util.HashMap;
import java.util.Map;

public class Message {
	
	private String topic = null;
	private Map<String,Object> data = new HashMap<String,Object>();
	private Map<String,Object> notification = new HashMap<String,Object>();
	
	public Message() {}
	
	public Message(String topic) {
		this.topic = topic;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Map getData() {
		return data;
	}
	public void setData(Map<String,Object> data) {
		this.data = data;
	}

	public Map getNotification() {
		return notification;
	}

	public void setNotification(Map<String,Object> notification) {
		this.notification = notification;
	}
	
	public void setTitle(String title) {
		data.put("title", title);
		notification.put("title", title);
	}
	
	public void setMessage(String message) {
		data.put("message", message);
		notification.put("body", message);
	}
	
}