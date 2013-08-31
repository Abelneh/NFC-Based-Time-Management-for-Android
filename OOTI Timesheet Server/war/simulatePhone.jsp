<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<FORM ACTION="nfcStore" METHOD="Post" 	ENCTYPE="application/x-www-form/urlencoded">

<table align="center" width="300px" >     
        	<tr>
        	     <td align="center"  colspan=2>
        	         <H2>Simulate Phone Scanning</H2>
        	     </td>
        	</tr>
        	
        	<tr>
				<td align="left"> NFC ID: </td>
				<td align="left"><INPUT TYPE="text" name = "nfcID" VALUE="02153112"><BR></td>
   
			</tr>
        	
        	<tr>
				<td align="left"> User Name: </td>
				<td align="left"><INPUT TYPE="text" name = "userName" VALUE="abelo@gmail.com"><BR></td>
   
			</tr>

			<tr>
				<td align="center"  colspan=2 ><INPUT TYPE="SUBMIT" NAME="Submit" VALUE="Upload Data"></td>
   
			</tr>
			
      </table>

</FORM>


</body>
</html>