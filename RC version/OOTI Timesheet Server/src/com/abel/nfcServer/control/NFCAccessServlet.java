package com.abel.nfcServer.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class NFCAccessServlet extends HttpServlet {
	
	
	
	//Shoul be done using Beans		
	Date entranceTime;
	Date exitTime;
	boolean lateComer;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
		PrintWriter out = response.getWriter();
        out.println("Android Get Request Received! Please Use Post Method!");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.flush();
		
		lateComer = checkEntranceTime(entranceTime);
		entranceTime = new Date();
		exitTime = new Date();
		 
		 if (lateComer){
			 try {
				sendWarning( request,  response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }

        out.println(storeData(request, response));
		
	}
	
		
    public String storeData(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
    	    		
    	String dataSaved = "Attempting to Store Data!";
    	
    	String NfcID = req.getParameter("nfcID");
		String userName = req.getParameter("userName");
		
		if (!NfcID.equals("000001")){
			//return "Invalid NFC tag read. Please Try agian!";
		}
		if(!userName.equals("Someone in the database")){
			//return "Scanning user doesn't Exist. Contact admin for Registration.";
		}
		

		exitTime.setTime(exitTime.getTime()+(1000*60*60*8));
		Entity empTimeInfo = new Entity("OOTIAccess", userName);
		
		empTimeInfo.setProperty("nfcID",NfcID);
		empTimeInfo.setProperty("userName",userName);
		empTimeInfo.setProperty("entranceTime",entranceTime);
		empTimeInfo.setProperty("exitTime", exitTime);
		empTimeInfo.setProperty("hourlyRate", 30);
		try {
			 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			 datastore.put(empTimeInfo);
			 dataSaved = "Success! Entrance Time Recorded!";
		}catch (Exception e){
			dataSaved = "Error!Entry Time Not Recorded!";	
		}
		
			
		return dataSaved;	
	}
    
    boolean checkEntranceTime(Date entranceTime){
    	
    	// if time > 9
    	
    	return true;
    	
    }
    
    private void sendWarning(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	
    	ServletContext context = this.getServletContext();
    	RequestDispatcher dispatcher = context.getRequestDispatcher("/sendAll");

    	// change your request and response accordingly

    	dispatcher.forward(req, resp);
    	 
 
	}
	
    
 
}
