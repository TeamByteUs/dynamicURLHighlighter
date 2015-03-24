
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class URLHightligtherServlet
 */
@WebServlet("/URLHightligtherServlet")
public class URLHightligtherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public URLHightligtherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.html";

		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join"; // default action
		}

		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			url = "/index.jsp"; // the "join" page
		} else if (action.equals("add")) {
			// get parameters from the request
			String userURL = request.getParameter("url");

			// validate the parameters
			String message;
			if (userURL == null || userURL.isEmpty()) {
				message = "Please input your URL.";
				url = "/index.jsp";
			} else {
				message = null;
				
				url = "/result.jsp";
			}
			request.setAttribute("message", message);
		}
		getServletContext().getRequestDispatcher(url)
				.forward(request, response);
	}
}
