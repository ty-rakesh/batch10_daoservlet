package com.tyss.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Connection con=null;
		Statement stmt = null;
		ResultSet st=null;
		try {
			con=DBConnection.initialize();
			stmt=con.createStatement();
			st = stmt.executeQuery("select * from user ");
			response.setContentType(getServletInfo());
			PrintWriter out = response.getWriter();
			out.print("Welcome to home page");
			while(st.next()) {
			String uid=st.getString("uid");
			String uname=st.getString("uname");
			String uphone=st.getString("uphone");
			String ugender=st.getString("ugender");
			out.println("\nuserid is: "+uid);
			out.println("\nUser name is : "+uname);
			out.println("\nPhone number is : "+uphone);
			out.println("\ngender is :"+ugender);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
