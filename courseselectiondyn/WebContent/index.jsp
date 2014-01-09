<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% java.util.Date d = new java.util.Date(); %>
<h1>
Welcome to the University Course Elective System! Today's date is <%= d.toString() %> 
</h1>
<FORM METHOD=POST ACTION="loginservlet.do">
username <INPUT TYPE=TEXT NAME=username SIZE=20><BR>
password <INPUT TYPE=TEXT NAME=password SIZE=20><BR>
<P><INPUT TYPE=SUBMIT value="Login">
</FORM>
</body>
</html>