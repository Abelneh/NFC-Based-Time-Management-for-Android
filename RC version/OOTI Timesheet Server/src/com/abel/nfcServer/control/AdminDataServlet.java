package com.abel.nfcServer.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abel.nfcServer.model.Employee;
import com.abel.nfcServer.logic.TimeSheet;


@SuppressWarnings("serial")
public class AdminDataServlet extends HttpServlet {
	
	TimeSheet empTimeSheet;
	Employee foundEmp;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
        out.println("Android Get Request Received! Please Use Post Method!");
	}

    public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
    	
    	PrintWriter out = resp.getWriter();
    	String userName = (String)req.getParameter("userName");
    	
    	empTimeSheet = new TimeSheet();    	
    	foundEmp = empTimeSheet.getEmployee(userName);
    	if(foundEmp == null){
    		 out.println("Employee "+ userName+ " Not Found.");
    	}
    	else{
	    	req.setAttribute("employeeBean", foundEmp);		
			req.getRequestDispatcher("/timeSheet.jsp").forward(req, resp); 
    	}
	}
    
    
	
}
