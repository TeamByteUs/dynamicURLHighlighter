import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/urlhighlighter")
public class URLHighlighterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// validate the parameters and send it back to Index.jsp if no userUrl
		String inUrl = request.getParameter("userUrl");
		if (inUrl == null || inUrl.isEmpty()) {
			errorOuput(request, response);
			return;
		}
		try {
			HTML htmlMarker = new HTML();
			String finalHTML = htmlMarker.mark(inUrl);
			response.getOutputStream().write(finalHTML.getBytes(Charset.forName("UTF-8")));
		} catch (Exception e) {
			request.setAttribute("message", "There was an error: " + e.getMessage());
			doGet(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void errorOuput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = (String) request.getAttribute("message");
		if (message == null) {
			message = "Please fill in the URL.";
		}
		
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/Index.jsp").forward(
				request, response);
	}
}
