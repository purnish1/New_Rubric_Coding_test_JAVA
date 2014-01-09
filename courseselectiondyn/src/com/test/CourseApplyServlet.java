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


/**
 * Servlet implementation class CourseApplyServlet
 */
@WebServlet("/CourseApplyServlet")
public class CourseApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId=Integer.parseInt(request.getParameter("id"));
		LoginData user=(LoginData)request.getSession().getAttribute("user");
		String forwardPage="studentLanding2.jsp";
		Connection con = null;  
		  Statement stmt = null;
		  ResultSet rs = null;
		  Statement stmt2 = null;
		  ResultSet rs2 = null;
		  ResultSet rs3=null;
		  Statement stmt3=null;
		  ResultSet rs4=null;
		  Statement stmt4=null;
		  ResultSet rs7=null;
		  Statement stmt7=null;

		  
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/courses","pdave","mysql");
		  stmt = con.createStatement();
		  stmt.executeUpdate("insert into studentcourses values (NULL,"+user.getId()+","+courseId+
		  ",'applied for')");
		  
		  stmt2=con.createStatement();
		  rs2=stmt2.executeQuery("select courselist.* from courselist left join studentcourses" +
		  		" on courselist.id=studentcourses.course_id where studentcourses.course_id" +
		  		" IS NULL");
		  List<Course> openCourseList=new ArrayList<Course>();
		  while(rs2.next()){
			  Course c=new Course();
			  c.setCourseName(rs2.getString(2));
			  c.setInstructor(rs2.getString(3));
			  c.setId(rs2.getInt(1));
			  openCourseList.add(c);
		  }
		  stmt3=con.createStatement();
		  rs3=stmt3.executeQuery("select courselist.id,courselist.coursename,courselist.instructor" +
		  		" from courselist inner join studentcourses on studentcourses.course_id=courselist.id " +
		  		"where studentcourses.student_id="+user.getId()+" and studentcourses.status=" +
		  				"'applied for'");
		  List<Course> appliedForCourseList=new ArrayList();
		  while(rs3.next()){
			  Course c=new Course();
			  c.setId(rs3.getInt(1));
			  c.setCourseName(rs3.getString(2));
			  c.setInstructor(rs3.getString(3));
			  appliedForCourseList.add(c);
		  }
		  request.setAttribute("appliedForCourseList", appliedForCourseList);

		  stmt7=con.createStatement();
		  rs7=stmt7.executeQuery("select courselist.id,courselist.coursename,courselist.instructor" +
		  		" from courselist inner join studentcourses on studentcourses.course_id=courselist.id " +
		  		"where studentcourses.student_id="+user.getId()+" and studentcourses.status=" +
		  				"'rejected/closed'");
		  List<Course> closedCourseList=new ArrayList<Course>();
		  while(rs7.next()){
			  Course c=new Course();
			  c.setId(rs7.getInt(1));
			  c.setCourseName(rs7.getString(2));
			  c.setInstructor(rs7.getString(3));
			  closedCourseList.add(c);
		  }
		  request.setAttribute("closedCourseList", closedCourseList);

		  
		  stmt4=con.createStatement();
		  rs4=stmt4.executeQuery("select courselist.id,courselist.coursename,courselist.instructor" +
		  		" from courselist inner join studentcourses on studentcourses.course_id=courselist.id " +
		  		"where studentcourses.student_id="+user.getId()+" and studentcourses.status=" +
		  				"'enrolled'");
		  List<Course> enrolledCourseList=new ArrayList<Course>();
		  while(rs4.next()){
			  Course c=new Course();
			  c.setId(rs4.getInt(1));
			  c.setCourseName(rs4.getString(2));
			  c.setInstructor(rs4.getString(3));
			  enrolledCourseList.add(c);
		  }
		  request.setAttribute("enrolledCourseList", enrolledCourseList);
		  request.setAttribute("openCourseList", openCourseList);
		  
		  
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
	  if(rs2 != null) {
	  rs2.close();
	  rs2 = null;
	  }
	  if(stmt2 != null) {
	  stmt2.close();
	  stmt2 = null;
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
