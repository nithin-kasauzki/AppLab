d)
<!DOCTYPE html>
<html>
<head>
    <title>Color Selection Form</title>
</head>
<body>
    <h2>Color Selection Form</h2>
    <form action="ColorServlet" method="get">
        <label for="color">Select a color:</label>
        <select id="color" name="color">
            <option value="Red">Red</option>
            <option value="Green">Green</option>
            <option value="Blue">Blue</option>
        </select><br><br>
        
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

public class ColorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String selectedColor = request.getParameter("color");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Selected Color</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Selected Color:</h2>");
        out.println("<p>" + selectedColor + "</p>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
