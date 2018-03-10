package com.neox.inventory.ws.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class GoogleService {
	
	public static String MESSAGE_SERVICE = "https://fcm.googleapis.com/v1/projects/neox-010118/messages:send";
	
	private static GoogleCredential credential = null;
	
	public static String sendMessage(String path, String content) throws IOException {
		StringBuffer str = new StringBuffer();
		URL url = new URL(MESSAGE_SERVICE);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String token = getAccessToken(path);
		
		conn.setRequestProperty("Authorization", "Bearer " + token);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		OutputStream out = conn.getOutputStream();
		out.write(content.getBytes());
		
		InputStream is = conn.getInputStream();
		byte[] b = new byte[1024];
		
		while(is.read(b) > 0) {
			str.append(new String(b));
		}
		
		return str.toString();
	}
	
	public static String getAccessToken(String path) throws FileNotFoundException, IOException {
		if(credential == null) {
			credential = GoogleCredential.
				fromStream(new FileInputStream(path + "service-account.json")).
				createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase","https://www.googleapis.com/auth/firebase.messaging","https://www.googleapis.com/auth/identitytoolkit"));
		}
		credential.refreshToken();
		return credential.getAccessToken();
	}
	
}
