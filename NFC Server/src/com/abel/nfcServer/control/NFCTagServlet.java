package com.abel.nfcServer.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class NFCTagServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
		PrintWriter out = response.getWriter();
        out.println("Android Get Request Received! Please Use Post Method!");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        out.println(storeData(request));
		
	}
	
		
    public String storeData(HttpServletRequest req)	throws IOException {
    	    		
    	String dataSaved = "Attempting to Store Data!";
    	
    	String NfcID = req.getParameter("nfcID");
		String userName = req.getParameter("userName");
		
		if (!NfcID.equals("000001")){
			//return "Invalid NFC tag read. Please Try agian!";
		}
		if(!userName.equals("Someone in the database")){
			//return "Scanning user doesn't Exist. Contact admin for Registration.";
		}
		
		//Shoul be done using Beans		
		Date entranceTime = new Date();
		Date exitTime = new Date();
		
		Entity empTimeInfo = new Entity("OOTIAccess", userName);
		
		empTimeInfo.setProperty("nfcID",NfcID);
		empTimeInfo.setProperty("userName",userName);
		empTimeInfo.setProperty("entranceTime",entranceTime);
		empTimeInfo.setProperty("exitTime", exitTime);
		
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(empTimeInfo);
			dataSaved = "Success! Entrance Time Recorded!";
		}catch (Exception e){
			dataSaved = "Error!Entry Time Not Recorded!";	
		}
	
	
		
		return dataSaved;	
	}
    
 
}
