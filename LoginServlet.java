package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VoterDaoImp;
import pojos.Voter;

@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImp dao;

	@Override
	public void init() throws ServletException {
		try {
			dao = new VoterDaoImp();
		} catch (Exception e) {
		
			throw new ServletException("error in init of " + getClass().getName(), e);

		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		try {
			dao.cleanUp();
		} catch (Exception e) {
			throw new RuntimeException("error in destroy of " + getClass().getName(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {

			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			Voter authenticateVoter = dao.authenticateVoter(email, password);
			if (authenticateVoter == null)
				pw.print("<h5>Invalid Login , Please <a href='login.html'>Retry</a></h5>");
			else {
				
				HttpSession session=request.getSession();
				System.out.println("Sesison ID "+session.getId());
		
				session.setAttribute("voter_info", authenticateVoter);
				
				pw.print("<h5>Please <a href='category'>Proceed To check Role of Voter</a></h5>");
	
			}

		} catch (Exception e1) {
			throw new ServletException("error in do-get of " + getClass().getName(), e1);
		}
	}

}
