package com.neox.inventory.ws.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Element implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2510111103439538325L;
	private String key;
	private String value;
	
	public Element() {}
	
	public Element(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "["+key+":"+value+"]";
	}
	
}
