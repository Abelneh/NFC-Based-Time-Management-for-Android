<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
<FORM ACTION="/nfcRetrieve" METHOD="Post" 	ENCTYPE="application/x-www-form/urlencoded">

<table align="center" width="500px" >     
        	<tr>
        	     <td align="center"  colspan=2>
        	         <H1>OOTI Time Sheet Management</H1>
        	     </td>
        	</tr>
        	
        	<tr>
        	     <td colspan=2 align="center">
        	         <H2>Admin Control Page</H2>
        	     </td>
        	</tr>
        	
        	<tr>
				<td align="center"> User Name: </td>
				<td align="left"><INPUT TYPE="text" name = "userName" VALUE="abelneh@gmail.com"><BR></td>
   
			</tr>

			<tr>
				<td align="center"> <INPUT TYPE="SUBMIT" NAME="Submit" VALUE="Employee Time Sheet"> </td>
				<td align="center"><INPUT TYPE="Button" VALUE="User Management"><BR></td>
   
			</tr>
			<tr>
        	     <td colspan=2 align="center">
        	         <H2><a href="simulatePhone.jsp">Phone Simulator</a></H2>
        	     </td>
        	</tr>
			
			
      </table>

</FORM>

</body>
</html>