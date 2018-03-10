package com.neox.inventory.ws.util;

import java.util.Base64;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import com.neox.inventory.service.UserService;

public class Auth {
	
	@Context
    ContainerRequestContext ctx;
	private String username;
	
	public boolean isAuthorized() {
		String hash = ctx.getHeaderString("authorization");
		System.out.println("hash: " + hash);
		String real = new String(Base64.getDecoder().decode(hash));
		StringTokenizer st = new StringTokenizer(real, ":");
		if(st.countTokens() < 2) {
			return false;
		}
		username = st.nextToken();
		return true;
	}

	public String getUsername() {
		if(!isAuthorized()) {
			return null;
		}
		return username;
	}
	
}
