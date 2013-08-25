package com.abel.nfc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
        out.println("Android Get Request Received! Please Use Post Method!");
	}

    public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
		
    	Key nfcKey = KeyFactory.createKey("OOTIAccess", "testUser");

		PrintWriter out = resp.getWriter();
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity employeeAbel;
		 
		try {
			employeeAbel = datastore.get(nfcKey); 
		   
		    String nfcID = (String) employeeAbel.getProperty("nfcID");
		    String userName = (String) employeeAbel.getProperty("userName");
		    String entranceTime = (String) employeeAbel.getProperty("entranceTime");
		    String exitTime = (String) employeeAbel.getProperty("exitTime");
		    
		    req.setAttribute("nfcID", nfcID);
		    req.setAttribute("userName", userName);
			req.setAttribute("entranceTime", entranceTime);
			req.setAttribute("exitTime", exitTime);
			req.getRequestDispatcher("/report.jsp").forward(req, resp);    
		   
		} catch (EntityNotFoundException e) {
			 out.println("Access Time Information Not Found!");
		}
		
	}
	
	
}
