<%@page import="com.test.AppliedForCourse"%>
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
    <%@ page import="com.test.LoginData" %>
    <%@ page import="java.util.*" %>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
LoginData luser=(LoginData)request.getSession().getAttribute("user");
response.getWriter().println("Welcome "+ luser.getUserName());

List<Course> professorCourses=(List<Course>)request.getAttribute("professorCourses");
Iterator<Course> iter=professorCourses.iterator();
response.getWriter().println("<table border ='1'>");
response.getWriter().println("<th>");
response.getWriter().println("My courses");
response.getWriter().println("</th>");
while(iter.hasNext()){
	Course c=iter.next();
	response.getWriter().println("<tr>");
	response.getWriter().println("<td>");
	response.getWriter().println(c.getCourseName());
	response.getWriter().println("</td>");
	response.getWriter().println("<td>");
	response.getWriter().println(c.getInstructor());
	response.getWriter().println("</td>");
	response.getWriter().println("</tr>");
}

response.getWriter().println("</table>");



List<AppliedForCourse> appliedForCourseList=(List<AppliedForCourse>)request.getAttribute("appliedForCourseList");
Iterator<AppliedForCourse> iter2=appliedForCourseList.iterator();
response.getWriter().println("<table border='1'>");
response.getWriter().println("<th>");
response.getWriter().println("Applied For courses");
response.getWriter().println("</th>");
while(iter2.hasNext()){
	AppliedForCourse afc=iter2.next();
	response.getWriter().println("<tr>");
	response.getWriter().println("<td>");
	response.getWriter().println(afc.getCourseName());
	response.getWriter().println("</td>");
	response.getWriter().println("<td>");
	response.getWriter().println(afc.getStudentId());
	response.getWriter().println("</td>");
	response.getWriter().println("<td>");
	response.getWriter().println(afc.getStatus());
	response.getWriter().println("</td>");
	response.getWriter().println("</tr>");
	response.getWriter().println("<td>");
	response.getWriter().println("<A HREF='http://localhost:8080/courseselectiondyn/processapplication?studentid="+afc.getStudentId()+
			"&courseid="+afc.getCourseId()+"&action=accept'>Accept"
			+"</A>");
	response.getWriter().println("</td>");
	response.getWriter().println("<td>");
	response.getWriter().println("<A HREF='http://localhost:8080/courseselectiondyn/processapplication?studentid="+afc.getStudentId()+
			"&courseid="+afc.getCourseId()+"&action=reject'>Reject"
			+"</A>");
	response.getWriter().println("</td>");
}

response.getWriter().println("</table>");
















%>

<FORM METHOD=POST ACTION="addcourse.do">
Course Name <INPUT TYPE=TEXT NAME=coursename SIZE=20><BR>
Course description <INPUT TYPE=TEXT NAME=coursedescription SIZE=20><BR>
<P><INPUT TYPE=SUBMIT value="Add course">
</FORM>


</body>
</html>