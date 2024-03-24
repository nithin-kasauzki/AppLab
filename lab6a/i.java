i)
<!DOCTYPE html>
<html>
<head>
    <title>Fruit Selection Form</title>
</head>
<body>
    <h2>Fruit Selection Form</h2>
    <form action="FruitServlet" method="post">
        <input type="checkbox" id="apple" name="fruit" value="Apple">
        <label for="apple">Apple</label><br>
        <input type="checkbox" id="orange" name="fruit" value="Orange">
        <label for="orange">Orange</label><br>
        <input type="checkbox" id="grapes" name="fruit" value="Grapes">
        <label for="grapes">Grapes</label><br>
        <input type="checkbox" id="mango" name="fruit" value="Mango">
        <label for="mango">Mango</label><br>
        <input type="checkbox" id="lemon" name="fruit" value="Lemon">
        <label for="lemon">Lemon</label><br>
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

public class FruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String[] selectedFruits = request.getParameterValues("fruit");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Selected Fruits</title></head>");
        out.println("<body>");
        out.println("<h2>Selected Fruits:</h2>");
        if (selectedFruits != null && selectedFruits.length > 0) {
            out.println("<ul>");
            for (String fruit : selectedFruits) {
                out.println("<li>" + fruit + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No fruits selected.</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}


Below is the HTML form (fruit_form.html) containing checkboxes for selecting fruit options and a submit button:		Now, let's create the HTTP servlet (FruitServlet.java) to retrieve the selected options and display them in the servlet window: