g)
<!DOCTYPE html>
<html>
<head>
    <title>Select Favorite Programming Language</title>
</head>
<body>
    <h2>Select Favorite Programming Language</h2>
    <form action="SessionServlet" method="post">
        <input type="radio" id="c" name="language" value="C">
        <label for="c">C</label><br>
        <input type="radio" id="cpp" name="language" value="C++">
        <label for="cpp">C++</label><br>
        <input type="radio" id="java" name="language" value="Java">
        <label for="java">Java</label><br>
        <input type="radio" id="vb6" name="language" value="VB 6">
        <label for="vb6">VB 6</label><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String language = request.getParameter("language");
        String isbn = getBookISBN(language);
        
        session.setAttribute(language, isbn);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Session Information</title></head>");
        out.println("<body>");
        out.println("<h2>Session Information:</h2>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p>Is New Session: " + session.isNew() + "</p>");
        out.println("<p>Creation Time: " + session.getCreationTime() + "</p>");
        out.println("<p>Last Accessed Time: " + session.getLastAccessedTime() + "</p>");
        out.println("<p>Maximum Inactive Interval: " + session.getMaxInactiveInterval() + " seconds</p>");
        out.println("<h2>Selected Language/Book ISBN Pairs:</h2>");
        
        for (String key : session.getAttributeNames()) {
            if (!key.equals("javax.servlet.jsp.jstl.fmt.request.charset")) {
                String value = session.getAttribute(key).toString();
                out.println("<p>" + key + ": " + value + "</p>");
            }
        }
        
        out.println("</body>");
        out.println("</html>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    private String getBookISBN(String language) {
        // This method should return the ISBN number for the recommended book
        // based on the selected language. This is just a placeholder.
        switch (language) {
            case "C":
                return "ISBN-C";
            case "C++":
                return "ISBN-C++";
            case "Java":
                return "ISBN-Java";
            case "VB 6":
                return "ISBN-VB6";
            default:
                return "Unknown";
        }
    }
}



SessionSelectLanguage.html: HTML form containing radio buttons for selecting a favorite programming language and a submit button. SessionServlet.java: Servlet to handle both GET and POST requests. It creates or retrieves a session object and adds the selected language and a recommended book ISBN to the session. Then, it generates an XHTML page displaying session information and the selected language/ISBN pairs.