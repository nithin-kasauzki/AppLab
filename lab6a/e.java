e)
<!DOCTYPE html>
<html>
<head>
    <title>Select Favorite Programming Language</title>
</head>
<body>
    <h2>Select Favorite Programming Language</h2>
    <form action="CookieServlet" method="post">
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String language = request.getParameter("language");
        Cookie cookie = new Cookie("favoriteLanguage", language);
        response.addCookie(cookie);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Favorite Language Saved</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Favorite Language Saved!</h2>");
        out.println("<p>You selected: " + language + "</p>");
        out.println("<a href=\"CookieBookRecommendations\">View Book Recommendations</a>");
        out.println("</body>");
        out.println("</html>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Cookie[] cookies = request.getCookies();
        String favoriteLanguage = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("favoriteLanguage")) {
                    favoriteLanguage = cookie.getValue();
                    break;
                }
            }
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Book Recommendations</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Book Recommendations</h2>");
        if (favoriteLanguage != null) {
            out.println("<p>Based on your favorite language (" + favoriteLanguage + "), here are some book recommendations:</p>");
            // Add book recommendations based on the selected language
        } else {
            out.println("<p>No favorite language selected. Please <a href=\"CookieSelectLanguage.html\">select a language</a> first.</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}

