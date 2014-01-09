package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Course> courseList=new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

/*    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub

    }
*/    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String forwardPage=null;
		Connection con = null;  
		  Statement stmt = null;
		  ResultSet rs = null;
		  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con =DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/courses","pdave","mysql");
		  stmt = con.createStatement();
		  rs = stmt.executeQuery("SELECT * FROM courselist");
		  // displaying records
		  while(rs.next()){
			  Course c=new Course();
			  c.setId(rs.getInt(1));
			  c.setCourseName(rs.getString(2));
			  c.setInstructor(rs.getString(3));
			  courseList.add(c);
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
		  if(con != null) {
		  con.close();
		  con = null;
		  }
		  } catch (SQLException e) {}
		  }
		  request.setAttribute("courseList", courseList);
		  courseList=new ArrayList();
		  forwardPage="studentLanding.jsp";
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
