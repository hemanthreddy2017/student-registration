import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	* @see Servlet#init(ServletConfig)
	*/
    Connection con = null;
    PreparedStatement ps=null;
    ServletContext context = null;
	public void init(ServletConfig config) throws ServletException {
		try
		{
			String s = config.getInitParameter("driver");
			context = config.getServletContext();
			Class.forName(s);
			String k = config.getInitParameter("url");
			String u = config.getInitParameter("user");
			String p = config.getInitParameter("pass");
			con = DriverManager.getConnection(k,u,p);
			ps = con.prepareStatement("insert into student_info values(?,?,?,?,?)");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* @see Servlet#destroy()
	*/
	public void destroy() {
		try {
			con.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int sno = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		String course = request.getParameter("course");
		double fee = Double.parseDouble(request.getParameter("fees"));
		String trainer = context.getInitParameter("tname");
		String btime = request.getParameter("check");
		int n=0;
		try {
			ps.setInt(1, sno);
			ps.setString(2, name);
			ps.setString(3, course);
			ps.setDouble(4, fee);
			ps.setString(5, btime);
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(n!=0)
		{
			out.println("Registration Successfully...");
			RequestDispatcher rd = request.getRequestDispatcher("/studentregistration.html");
			rd.include(request, response);
			out.println("developed by "+trainer);
		}else{
			out.println("Registration Not  Successfull...");
			RequestDispatcher rd = request.getRequestDispatcher("/studentregistration.html");
			rd.include(request, response);
			out.println("developed by "+trainer);
		}
	}

}