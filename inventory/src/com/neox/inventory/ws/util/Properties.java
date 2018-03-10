package com.neox.inventory.ws.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {
	
	private static java.util.Properties file = null;
	private static String filePath = null;
	
	public static void loadFile(String path) {
		if(file != null) {
			return;
		}
		System.out.println("Cargando datos...");
		file = new java.util.Properties();
		filePath = path;
		try {
			file.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reload() {
		if(filePath == null) {
			System.out.println("No se ha cargado el archivo de configuración imposible recargarlo");
			return;
		}
		file = new java.util.Properties();
		try {
			file.load(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return (file == null)?null:file.getProperty(key);
	}
	
}
