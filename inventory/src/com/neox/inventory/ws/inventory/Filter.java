package com.neox.inventory.ws.inventory;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.neox.inventory.service.UserService;
import com.neox.inventory.ws.util.Properties;

@Provider
@PreMatching
public class Filter implements ContainerRequestFilter {
	
	@Context
    private ServletContext context;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		Properties.loadFile(context.getRealPath("/WEB-INF/config.properties"));
		//*
		System.out.println(requestContext.getUriInfo().getBaseUri() + " => " + requestContext.getUriInfo().getPath());
		String authorization = requestContext.getHeaderString("authorization");
		String authorize = requestContext.getHeaderString("authorize");
		
		System.out.println("authorize: " + authorize);
		System.out.println("authorization: " + authorization);
		//*/
		if(authorize == null && authorization == null) {
			return;
		}
		if(authorize == null || authorization == null) {
			requestContext.setRequestUri(URI.create(requestContext.getUriInfo().getBaseUri() + "config/notAuthorized.json"));
			return;
		}
		
		String real = new String(Base64.getDecoder().decode(authorization));
		StringTokenizer st = new StringTokenizer(real, ":");
		if(st.countTokens() < 2) {
			requestContext.setRequestUri(URI.create(requestContext.getUriInfo().getBaseUri() + "config/notAuthorized.json"));
			return;
		}
		String username = st.nextToken();
		String passwd = st.nextToken();
		if(UserService.isUser(username, passwd) == null) {
			requestContext.setRequestUri(URI.create(requestContext.getUriInfo().getBaseUri() + "config/notAuthorized.json"));
			return;
		}
		
	}

}
