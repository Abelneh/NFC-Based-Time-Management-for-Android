<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Timesheet </title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
   <body>    
     <jsp:useBean id="employeeBean" class="com.abel.nfcServer.model.Employee" type="com.abel.nfcServer.model.Employee" scope="application"> 
        <jsp:getProperty name="employeeBean" property="userName" />
     </jsp:useBean>
     <%
        java.util.Date entranceDate =  new java.util.Date ();//(java.util.Date) application.getAttribute("entranceTime");
        java.util.Date exitDate =  new java.util.Date ();//(java.util.Date) application.getAttribute("exitTime");

      %>

      <table align="center"  width="500px">     
        	<tr>
        	     <td align="center"  colspan=5>
        	         <h2>Overview of Entrance Time</h2>
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
			   <td><% out.println(entranceDate.toString()); %> </td>
			   <td><% out.println(entranceDate.toString()); %> </td>
			</tr>

      </table>
      
      
   </body>
</html>