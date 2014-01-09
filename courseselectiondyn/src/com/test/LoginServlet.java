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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		String userName=request.getParameterValues("username")[0];
		String password=request.getParameterValues("password")[0];
		LoginData loginData=new LoginData();
		loginData.setUserName(userName);
		loginData.setPassword(password);
		
		String forwardPage="index.jsp";
		Connection con = null;  
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
		  ResultSet rs7=null;
		  Statement stmt7=null;
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/courses","pdave","mysql");
		  stmt = con.createStatement();
		  rs = stmt.executeQuery("SELECT * FROM users where users.username='"+userName+"'");
		  // displaying records
		  while(rs.next()){
		  if(rs.getObject(3).toString().equals(password)){
			  loginData.setId(rs.getInt(1));
				request.getSession().setAttribute("user", loginData);

			  forwardPage="courses.jsp";
			  if(rs.getObject(4).toString().equals("professor")){
				  forwardPage="professorLanding.jsp";
				  //forwardPage="professorLogin.do";
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
				  		"'"+loginData.getUserName()+"' and studentcourses.status='applied for'");
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
			  if(rs.getObject(4).toString().equals("student")){
				  forwardPage="studentLanding2.jsp";
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
				  		"where studentcourses.student_id="+loginData.getId()+" and studentcourses.status=" +
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
				  		"where studentcourses.student_id="+loginData.getId()+" and studentcourses.status=" +
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
				  		"where studentcourses.student_id="+loginData.getId()+" and studentcourses.status=" +
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
				  //forwardPage="studentLogin.do";
			  }
		  }

		  }
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

}
