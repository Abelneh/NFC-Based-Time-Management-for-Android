package com.abel.nfc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class NFCServer extends HttpServlet {
	
	Calendar currentTime;

	
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
		
				
		currentTime = Calendar.getInstance();
		String entranceTime =  java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		
		Entity empTimeInfo = new Entity("OOTIAccess", "testUser");
		empTimeInfo.setProperty("nfcID",NfcID);
		empTimeInfo.setProperty("userName",userName);
		empTimeInfo.setProperty("entranceTime",entranceTime);
		empTimeInfo.setProperty("exitTime","tba");
		
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(empTimeInfo);
			dataSaved = "Success! Entrance Time Recorded!";
		}catch (Exception e){
			dataSaved = "Error!Entry Time Not Recorded!";	
		}
	
	
		
		return dataSaved;	
	}
    
  /*  	
    private String requestParser(HttpServletRequest request){
		
		String requestData = "Received Values are: ";
		
		Enumeration<String> requestValues = request.getParameterNames();
		
	    while(requestValues.hasMoreElements()){
	        String param = requestValues.nextElement();
	        requestData = requestData + request.getParameter(param);
	    }
	    
		
		String NfcID = request.getParameter("nfcID");
		String userName = request.getParameter("userName");
		String entranceTime = request.getParameter("entranceTime");
		requestData = requestData + NfcID + ", "+ userName + ", "+ entranceTime + ".";
		
		return requestData;
	}
	*/
}
