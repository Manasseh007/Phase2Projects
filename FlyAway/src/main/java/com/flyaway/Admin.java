package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public Admin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String user="root";
		String password="Terr7#nce";
		String url="jdbc:mysql://localhost:3306/FlyAway";
		Connection con=DriverManager.getConnection(url,user,password);
		PreparedStatement ps=con.prepareStatement("select * from admin");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			if(request.getParameter("username").equals(rs.getString(1))&&request.getParameter("password").equals(rs.getString(2))) {
				request.getRequestDispatcher("1.html").include(request, response);
			}
			else {
				request.getRequestDispatcher("index.html").include(request, response);
				pw.println("<h3 align='right'><SPAN style='color:red'>Invalid Credentials</SPAN></h3>");
			}
		}
		
	}
		catch (Exception e) {
			
			pw.print(e);
		}
		
	}
}