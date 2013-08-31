<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Sheet Demo </title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
   <body>    
     <jsp:useBean id="employeeBean" class="com.abel.nfcServer.model.Employee" type="com.abel.nfcServer.model.Employee" scope="application"> 
     </jsp:useBean>
     
    
      <table align="center"  width="500px">     
        	<tr>
        	     <td align="center"  colspan=5>
        	         <h2>Demo Timesheet</h2>
        	     </td>
        	</tr>        	
        	<tr>
        	     <td colspan=5>
        	         <h3>User Name:  <jsp:getProperty name="employeeBean" property="userName" />  </h3>
        	     </td>
        	</tr>
			<tr>
			   <td>Entrance Time</td>
			   <td>Exit Time</td>
			</tr>
			<tr>
			   <td><jsp:getProperty name="employeeBean" property="entranceTime" />   </td>
			   <td><jsp:getProperty name="employeeBean" property="exitTime" />   </td>
			</tr>
			<tr>
        	     
        	     <td colspan=5>
        	         <h4>Salary for the Specified Duration:  <jsp:getProperty name="employeeBean" property="salary" />  Euros.</h4>
        	     </td>
        	       
        	</tr>
			

      </table>
      
      
   </body>
</html>