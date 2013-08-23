package com.abel.nfc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class AdminDataServlet extends HttpServlet {
	
	/*
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
		Key nfcKey = KeyFactory.createKey("NFCTag", "OOTITag");

		PrintWriter out = resp.getWriter();
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity employeeAbel;
		
		 out.println("Step one");
		 
		try {
			employeeAbel = datastore.get(nfcKey);
		   
		    String nfcID = (String) employeeAbel.getProperty("nfcID");
		    String userName = (String) employeeAbel.getProperty("userName");
		    String accessTime = (String) employeeAbel.getProperty("entranceTime");
 
	        out.println("Access Time Information !");
	        out.println("NFC ID: " + nfcID);
	        out.println("userName: " + userName);
	        out.println("Access Time: " + accessTime);
		    
		   
		} catch (EntityNotFoundException e) {
			 out.println("Access Time Information Not Found!");
		}
		
	}
	*/
    public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
    	Key nfcKey = KeyFactory.createKey("NFCTag", "OOTITag");

		PrintWriter out = resp.getWriter();
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity employeeAbel;
		 
		try {
			employeeAbel = datastore.get(nfcKey); 
		   
		    String nfcID = (String) employeeAbel.getProperty("nfcID");
		    String userName = (String) employeeAbel.getProperty("userName");
		    String accessTime = (String) employeeAbel.getProperty("entranceTime");
 
	        out.println("Access Time Information !"); 
	        out.println("NFC ID: " + nfcID);
	        out.println("userName: " + userName);
	        out.println("Access Time: " + accessTime);
		    
		   
		} catch (EntityNotFoundException e) {
			 out.println("Access Time Information Not Found!");
		}
		
	}
	
	
}
