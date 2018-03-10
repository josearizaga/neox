package com.neox.inventory.ws.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599387794572981084L;
	private String token;
	
	public Token() {}
	
	public Token(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
