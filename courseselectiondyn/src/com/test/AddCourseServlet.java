package com.test;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseName=request.getParameterValues("coursename")[0];
		String courseDesc=request.getParameterValues("coursedescription")[0];
		LoginData user=(LoginData)request.getSession().getAttribute("user");
		String forwardPage="professorLanding.jsp";
		  Statement stmt = null;
		  ResultSet rs = null;
		  Statement stmt5 = null;
		  ResultSet rs5 = null;
		  Statement stmt6 = null;
		  ResultSet rs6 = null;
		  Connection con=null;
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/courses","pdave","mysql");
		  stmt = con.createStatement();
		  stmt.executeUpdate("insert into courselist (id,coursename,instructor) values" +
		  		" (NULL,'"+courseName+"','"+user.getUserName()+"')");
		  
		  List<Course> professorCourses=new ArrayList<Course>();
		  stmt5=con.createStatement();
		  rs5=stmt5.executeQuery("select * from courselist where instructor='"+user.getUserName()+"'");
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
		  		"'"+user.getUserName()+"' and studentcourses.status='applied for'");
		  while(rs6.next()){
			  AppliedForCourse afc=new AppliedForCourse();
			  afc.setStudentId(rs6.getInt(1));
			  afc.setCourseId(rs6.getInt(2));
			  afc.setCourseName(rs6.getString(3));
			  afc.setStatus(rs6.getString(4));
			  appliedForCourseList.add(afc);
		  }
		  request.setAttribute("appliedForCourseList", appliedForCourseList);
		  
		  } catch (SQLException e) {
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
		  if(con != null) {
		  con.close();
		  con = null;
		  }
		  } catch (SQLException e) {}
		  }

		RequestDispatcher view = request.getRequestDispatcher(forwardPage);
        view.forward(request, response);

		
	}

}
