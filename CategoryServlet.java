package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Voter;
//import pojos.Candidate;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Login Successful </h5>");
			
			HttpSession hs = request.getSession();
			Voter v = (Voter) hs.getAttribute("voter_info");
					if (v!=null)
					{
						pw.print("Voter Details via HttpSession  " + v);
						if(v.getRole().contentEquals("voter")) {
							pw.print("<br> Is Voter <br>");
						//	pw.print("<h5><a href='choose_candidate.html'>Vote Candidate</a></h5>");		
							response.sendRedirect("CandidateListServlet");
						}
						else {
							pw.print("<br> Is Admin <br> ");
							response.sendRedirect("adminpage");
						}
			} else
				pw.print("<h5>No Session Tracking!!!!!</h5>");

			pw.print("<h5><a href='logout'>Log Me Out</a></h5>");
		}
	}

}
