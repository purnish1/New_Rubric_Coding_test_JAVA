package com.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessApplicationServlet
 */
@WebServlet("/ProcessApplicationServlet")
public class ProcessApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId=Integer.parseInt(request.getParameter("courseid"));
		int studentId=Integer.parseInt(request.getParameter("studentid"));
		String status=request.getParameter("status");
		String action=request.getParameter("action");
		LoginData loginData=(LoginData)request.getSession().getAttribute("user");
		String forwardPage="professorLanding.jsp";
		 
		  Statement stmt = null;
		  ResultSet rs = null;
		  Statement stmt2 = null;
		  ResultSet rs2 = null;
		  ResultSet rs3=null;
		  Statement stmt3=null;
		  ResultSet rs4=null;
		  Statement stmt4=null;
		  ResultSet rs5=null;
		  Statement stmt5=null;
		  ResultSet rs6=null;
		  Statement stmt6=null;
		  
		  Connection con=null;
		  
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/courses","pdave","mysql");
		  stmt = con.createStatement();
		  if(action.equals("accept")){
			  stmt=con.createStatement();
			  stmt.executeUpdate("update studentcourses set status='enrolled' where" +
			  		" student_id="+studentId+" and course_id="+courseId);
		  }
		  else if(action.equals("reject")){
			  stmt2=con.createStatement();
			  stmt2.executeUpdate("update studentcourses set status='rejected/closed' where" +
			  		" student_id="+studentId+" and course_id="+courseId);
		  }
		  List<Course> professorCourses=new ArrayList<Course>();
		  stmt5=con.createStatement();
		  rs5=stmt5.executeQuery("select * from courselist where instructor='"+loginData.getUserName()+"'");
		  while(rs5.next()){
			  Course c=new Course();
			  c.setId(rs5.getInt(1));
			  c.setCourseName(rs5.getString(2));
			  c.setInstructor(rs5.getString(3));
			  professorCourses.add(c);
		  }
		  request.setAttribute("professorCourses", professorCourses);
		  List<AppliedForCourse> appliedForCourseList=new ArrayList<AppliedForCourse>();
		  stmt6=con.createStatement();
		  rs6=stmt6.executeQuery("select studentcourses.student_id,studentcourses.course_id" +
		  		",courselist.coursename,studentcourses.status from studentcourses inner join " +
		  		"courselist on studentcourses.course_id=courselist.id where courselist.instructor=" +
		  		"'"+loginData.getUserName()+"' and studentcourses.status='applied for' or" +
		  				" studentcourses.status='rejected/closed'");
		  while(rs6.next()){
			  AppliedForCourse afc=new AppliedForCourse();
			  afc.setStudentId(rs6.getInt(1));
			  afc.setCourseId(rs6.getInt(2));
			  afc.setCourseName(rs6.getString(3));
			  afc.setStatus(rs6.getString(4));
			  appliedForCourseList.add(afc);
		  }
		  request.setAttribute("appliedForCourseList", appliedForCourseList);

		  }
	   catch (SQLException e) {
	 throw new ServletException("Servlet Could not display records.", e);
	  } catch (ClassNotFoundException e) {
	  throw new ServletException("JDBC Driver not found.", e);
	  } finally {
	  try {
	  if(rs != null) {
	  rs.close();
	  rs = null;
	  }
	  if(stmt != null) {
	  stmt.close();
	  stmt = null;
	  }
	  if(rs2 != null) {
	  rs2.close();
	  rs2 = null;
	  }
	  if(stmt2 != null) {
	  stmt2.close();
	  stmt2 = null;
	  }
	  if(rs3 != null) {
	  rs3.close();
	  rs3 = null;
	  }
	  if(stmt3 != null) {
	  stmt3.close();
	  stmt3 = null;
	  }
	  if(rs4 != null) {
	  rs4.close();
	  rs4 = null;
	  }
	  if(stmt4 != null) {
	  stmt4.close();
	  stmt4 = null;
	  }
	  if(con != null) {
	  con.close();
	  con = null;
	  }
	  } catch (SQLException e) {}
	  }

		  
		RequestDispatcher view = request.getRequestDispatcher(forwardPage);
	    view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
