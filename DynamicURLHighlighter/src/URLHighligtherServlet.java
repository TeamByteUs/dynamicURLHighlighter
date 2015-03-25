

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.GenericServlet; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import team.misc.ArrayOrganizer;
import team.misc.BinarySearcher;
import team.misc.ConsoleOutputResults;
import team.misc.CustomUrlPrompter;
import team.misc.CustomWordListPrompter;
import team.misc.HtmlOutput;
import team.misc.MarkUpText;
import team.misc.URLContentExtractor;

public class URLHighligtherServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.html";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.jsp";    // the "join" page
        } 
        else if (action.equals("add")) {    // get parameters from the request
            String inUrl = request.getParameter("url");
            String finalHTML = HTML.mark(inUrl);
            request.setAttribute("final", finalHTML);
       

            // validate the parameters
            String message;
            if (url == null || url.isEmpty())  {
                message = "Please fill in the URL.";
                url = "/index.jsp";
            } 
            else {
                message = null;
                url = "/result.jsp";
            }
            request.setAttribute("final", finalHTML); //do you set attribute here or about in line 54? 
        }
     
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }    
}
