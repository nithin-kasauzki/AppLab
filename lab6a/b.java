import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve the specified URL from the request (if provided)
    String targetUrl = request.getParameter("url");

    response.setContentType("text/html"); // Set content type as XHTML
    PrintWriter out = response.getWriter();

    if (targetUrl != null) {
      try {
        // Retrieve the content of the specified URL
        URL url = new URL(targetUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder responseContent = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          responseContent.append(inputLine).append("\n");
        }
        in.close();

        // Display the retrieved content
        out.println(responseContent.toString());
      } catch (IOException e) {
        // Handle any errors during retrieval
        out.println("<h2>Error retrieving content from URL: " + targetUrl + "</h2>");
        e.printStackTrace(); // Log the error for debugging
      }
    } else {
        //no need to write the else part
      // If no URL is specified, generate a default response
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Welcome to Servlets!</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Welcome to Servlets!</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }
}
