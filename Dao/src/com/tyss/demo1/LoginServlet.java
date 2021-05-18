package com.tyss.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		try
		{
			con=DBConnection.initialize();
			stmt=con.createStatement();
			st = stmt.executeQuery("select * from login ");
			response.setContentType(getServletInfo());
			PrintWriter out = response.getWriter();
			String email1=request.getParameter("email");
			String pwd1=request.getParameter("password");

            int count=0;
			//4.process the result

			while(st.next())
			{
				String email2=st.getString("email");
				String pwd2=st.getString("password");
				if(email1.equals(email2) && pwd1.equals(pwd2))
				{
					
					count++;
				}
			}
			if(count>0)
			{
			
				RequestDispatcher dispatcher1= request.getRequestDispatcher("WelcomeServlet");
				dispatcher1.forward(request, response);
			}
			else
			{
				out.print("username or password is incorrect");
				RequestDispatcher dispatcher1= request.getRequestDispatcher("dbLogin1.html");
				dispatcher1.include(request, response);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {


			try {
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(st!=null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
