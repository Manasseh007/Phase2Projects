package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet Filter implementation class Validation
 */
@WebFilter("/Validation")
public class Validation extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String cardno=request.getParameter("cardno");
		if(cardno==null||!cardno.matches("[0-9]{12}")||!request.getParameter("cvv").matches("[0-9]{3}")){
			PrintWriter pw=response.getWriter();
			request.getRequestDispatcher("payment.html").include(request, response);
			pw.println("<center><SPAN style='color:red'>Invalid Credentials</SPAN></center>");
		}
		else {
			Customer c=new Customer();
			c.setCardname(request.getParameter("name1"));
			c.setCardno(Long.parseLong(request.getParameter("cardno")));
			request.setAttribute("customer", c);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}