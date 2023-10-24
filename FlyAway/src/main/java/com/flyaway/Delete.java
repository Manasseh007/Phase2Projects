package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
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
		
		PreparedStatement ps=con.prepareStatement("delete from flights where flightnumber=?");
		ps.setString(1, request.getParameter("flightnos"));
		ps.executeUpdate();
		ps.close();
		request.getRequestDispatcher("show-flights.jsp").include(request, response);
		pw.println("<center><SPAN style='color:green'>Flight Deleted Successfully or entered Flight Number doesn't exist</SPAN></center>");
		
		}
		catch (Exception e) {
			
			pw.print(e);
		}
	}

} 