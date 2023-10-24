package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
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
		PreparedStatement ps=con.prepareStatement("select * from admin");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			if(request.getParameter("newpassword").equals(request.getParameter("conpassword"))&&request.getParameter("oldpassword").equals(rs.getString(2))&&!request.getParameter("newpassword").equals(rs.getString(2))) {
				PreparedStatement ps1=con.prepareStatement("update admin set Password=? where Username=?");
				ps1.setString(1, request.getParameter("newpassword"));
				ps1.setString(2, "Terrence");
				ps1.executeUpdate();
				ps1.close();
				request.getRequestDispatcher("cp.html").include(request, response);
				pw.println("<center><SPAN style='color:green'>Password Changed Successfully</SPAN></center>");
			}
			else {
				request.getRequestDispatcher("cp.html").include(request, response);
				pw.println("<center><SPAN style='color:red'>Invalid Credentials</SPAN></center>");
			}
		}
		
	}
		catch (Exception e) {
			
			pw.print(e);
		}
	
	}

} 