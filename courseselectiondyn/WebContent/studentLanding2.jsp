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
<jsp:useBean id="user" class="com.test.LoginData" scope="session"></jsp:useBean>
<%
LoginData luser=(LoginData)request.getSession().getAttribute("user");
response.getWriter().println("Welcome "+ luser.getUserName());

%>
<%List<Course> openCourseList= (List<Course>)request.getAttribute("openCourseList");%>
<%Iterator<Course> iter=openCourseList.iterator();
List<Course> appliedForCourseList=(List<Course>)request.getAttribute("appliedForCourseList");
List<Course> enrolledCourseList=(List<Course>)request.getAttribute("enrolledCourseList");
response.getWriter().println("<table border='1'>");
response.getWriter().println("<th>");
response.getWriter().println("Open courses");
response.getWriter().println("</th>");
response.getWriter().println("<th>");
response.getWriter().println("Instructor");
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
	response.getWriter().println("<td>");
	response.getWriter().println("<A HREF='http://localhost:8080/courseselectiondyn/courseapply.do?id="+c.getId()+"'>Apply"
			+"</A>");
	response.getWriter().println("</td>");
}

response.getWriter().println("</table>");

List<Course> closedCourses=(List<Course>)request.getAttribute("closedCourseList");
response.getWriter().println("<table border='1'>");
response.getWriter().println("<th>");
response.getWriter().println("Rejected/closed courses");
response.getWriter().println("</th>");
response.getWriter().println("<th>");
response.getWriter().println("Instructor");
response.getWriter().println("</th>");
iter=closedCourses.iterator();
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


iter=appliedForCourseList.iterator();
response.getWriter().println("<table border='1'>");
response.getWriter().println("<th>");
response.getWriter().println("Applied for courses");
response.getWriter().println("</th>");
response.getWriter().println("<th>");
response.getWriter().println("Instructor");
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

iter=enrolledCourseList.iterator();
response.getWriter().println("<table border ='1'>");
response.getWriter().println("<th>");
response.getWriter().println("Enrolled courses");
response.getWriter().println("</th>");
response.getWriter().println("<th>");
response.getWriter().println("Instructor");
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


%>
</body>
</html>