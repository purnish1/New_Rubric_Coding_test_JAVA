<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="java.io.IOException"%>
	<%@ page import="javax.servlet.RequestDispatcher"%>
	<%@ page import="javax.servlet.ServletException"%>
	<%@ page import="javax.servlet.annotation.WebServlet"%>
	<%@ page import="javax.servlet.http.HttpServlet"%>
	<%@ page import="javax.servlet.http.HttpServletRequest"%>
	<%@ page import="javax.servlet.http.HttpServletResponse"%>
    <%@ page import="com.test.Course" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" class="com.test.LoginData" scope="session"></jsp:useBean>
Welcome ${user.userName}
<%List<Course> courseList= (List<Course>)request.getAttribute("courseList");%>
<%Iterator<Course> iter=courseList.iterator();
while(iter.hasNext()){
	Course c=iter.next();
	response.getWriter().println("<table>");
	response.getWriter().println("<tr>");
	response.getWriter().println("<td>");
	response.getWriter().println(c.getCourseName()+"\n");
	response.getWriter().println("</td>");
	response.getWriter().println("<td>");
	response.getWriter().println(c.getInstructor()+"\n");
	response.getWriter().println("</td>");
/* 	response.getWriter().println("<td>");
	response.getWriter().println("<A HREF='http://localhost:8080/courseselectiondyn/courseapply.jsp?id=/>" + 
	c.getId()+ "</A>");
	response.getWriter().println("</td>");
 */	response.getWriter().println("</tr>");
	response.getWriter().println("</table>");
%>
<%} %>
</body>
</html>