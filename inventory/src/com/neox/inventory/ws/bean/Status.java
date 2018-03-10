package com.neox.inventory.ws.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5904814774778774297L;
	private String status;
    private String message;
    private java.util.List<Object> list = new java.util.ArrayList<Object>();
    
    public Status() {} // needed for JAXB
    
    public Status(String status, String message) {
        this.status = status;
        this.message = message;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public java.util.List<Object> getList() {
		return list;
	}

	public void setList(java.util.List<Object> list) {
		this.list = list;
	}
    
}
