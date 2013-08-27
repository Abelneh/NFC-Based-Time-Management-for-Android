package com.abel.nfcServer.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abel.nfcServer.model.Employee;
import com.abel.nfcServer.model.EmployeeLookUp;


@SuppressWarnings("serial")
public class AdminDataServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
        out.println("Android Get Request Received! Please Use Post Method!");
	}

    public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
    	
    	PrintWriter out = resp.getWriter();
    	String userName = (String)req.getParameter("userName");
    	
    	EmployeeLookUp empLookUp = new EmployeeLookUp();    	
    	Employee foundEmp = empLookUp.findEmployee(userName);
    	if(foundEmp == null){
    		 out.println("Employee "+ userName+ " Not Found.");
    	}
    	else{
	    	req.setAttribute("employeeBean", foundEmp);		
			req.getRequestDispatcher("/report.jsp").forward(req, resp); 
    	}
	}
	
	
}
