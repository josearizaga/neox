package com.neox.inventory.web.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Configuration {

	public static final Integer MAX_TEXT_AREA = 128;
	public static final Integer DOUBLE_TEXT_AREA = 128;
	public static final Integer MAX_TEXT_INPUT = 32;
	public static final Integer MAX_TEXT_AREA_LEN = 160;
	public static final Integer MAX_MAIL_INPUT = 64;
	
	public static final String validMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String rowsPerPage = "5,10,15,20,25,30,50,100,125,150,200,250,300,500";
	
	public static final Integer idleTimeout = (1000 * 60)*(15);
	public static final Integer rows = 100;
	
	
}
