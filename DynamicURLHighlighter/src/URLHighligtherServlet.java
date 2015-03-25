import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/urlhighlighter")
public class URLHighligtherServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get current action
		String action = request.getParameter("action");
		if (action.equals("add")) { // get parameters from the request

			// validate the parameters and send it back to Index.jsp
			String message;
			String inUrl = request.getParameter("userUrl");
			if (inUrl == null || inUrl.isEmpty()) {
				message = "Please fill in the URL.";
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/Index.jsp").forward(
						request, response);
			} else {
				String finalHTML = HTML.mark(inUrl);
				response.getWriter().write(finalHTML);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
