

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet2
 */
public class StudentServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("stname");
		out.println("**********************   Welcome to Student details   ***********************");
		out.println("<br/>");
		out.println("Student name is "+name);
		out.println("<br/>");
		int no= Integer.parseInt(request.getParameter("stno"));
		out.println("Sudent number is "+no);
		out.println("<br/>");
		String course = request.getParameter("course");
		out.println("Student course is "+course);
		
	}

}
