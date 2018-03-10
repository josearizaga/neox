package com.neox.inventory.ws.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("catalog")
public class Catalog {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

  // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
		  return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }

  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
		  return "<html> " + "<title>" + "Hello Jersey 2" + "</title>"
				  + "<body><h1>" + "Hello Jersey 2" + "</body></h1>" + "</html> ";
	  }
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("contact/{name}/{number}")
	  public List<Contact> getContacts(
			  @PathParam("name") String name,
			  @PathParam("number") String number) {
		  List<Contact> c = new ArrayList<Contact>();
		  c.add(new Contact("uno", "1"));
		  c.add(new Contact("dois", "2"));
		  c.add(new Contact("three", "3"));
		  c.add(new Contact("cuatro", "4"));
		  c.add(new Contact(name, number));
		  return c;
	  }
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("contact")
	  public List<Contact> getContacts(
			  @QueryParam("name") String name) {
		  List<Contact> c = new ArrayList<Contact>();
		  c.add(new Contact("uno", "1"));
		  c.add(new Contact("dois", "2"));
		  c.add(new Contact("three", "3"));
		  c.add(new Contact("cuatro", "4"));
		  System.out.println("Name: " + name);
		  return c;
	  }
	
}

class Contact {
	private String name;
	private String number;
	
	public Contact(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
