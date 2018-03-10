package com.neox.inventory.ws.uri;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("app")
public class AppServices extends ResourceConfig {
	
	public AppServices() {
		System.out.println("Tocando la configuración...");
		packages("com.neox.inventory.ws.app");
		packages("com.neox.inventory.ws.inventory");
	}
	
}
