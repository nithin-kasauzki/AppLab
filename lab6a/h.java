h)
<!DOCTYPE html>
<html>
<head>
    <title>Animal Survey</title>
</head>
<body>
    <h2>Animal Survey</h2>
    <form action="SurveyServlet" method="post">
        <input type="radio" id="dog" name="animal" value="Dog">
        <label for="dog">Dog</label><br>
        <input type="radio" id="cat" name="animal" value="Cat">
        <label for="cat">Cat</label><br>
        <input type="radio" id="parrot" name="animal" value="Parrot">
        <label for="parrot">Parrot</label><br>
        <input type="radio" id="cow" name="animal" value="Cow">
        <label for="cow">Cow</label><br>
        <input type="radio" id="none" name="animal" value="None">
        <label for="none">None</label><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurveyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Database connection parameters
        String url = "jdbc:cloudscape:demo";
        String username = "username";
        String password = "password";
        
        // Retrieve selected animal from the form
        String animal = request.getParameter("animal");
        
        // Update the database with the vote for the selected animal
        updateVotes(animal, url, username, password);
        
        // Retrieve survey results from the database
        String[][] surveyResults = getSurveyResults(url, username, password);
        
        // Generate XHTML document dynamically
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Survey Results</title></head>");
        out.println("<body>");
        out.println("<h2>Survey Results</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Animal</th><th>Votes</th><th>Percentage</th></tr>");
        
        int totalVotes = 0;
        for (String[] row : surveyResults) {
            String animalName = row[0];
            int votes = Integer.parseInt(row[1]);
            totalVotes += votes;
            
            double percentage = (double) votes / totalVotes * 100;
            out.println("<tr><td>" + animalName + "</td><td>" + votes + "</td><td>" + String.format("%.2f%%", percentage) + "</td></tr>");
        }
        
        out.println("</table>");
        out.println("<p>Total Responses: " + totalVotes + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void updateVotes(String animal, String url, String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE Survey SET Votes = Votes + 1 WHERE Animal = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, animal);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String[][] getSurveyResults(String url, String username, String password) {
        String[][] results = new String[5][2]; // Assuming 5 animals
        
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Survey";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    int index = 0;
                    while (rs.next()) {
                        String animal = rs.getString("Animal");
                        int votes = rs.getInt("Votes");
                        results[index][0] = animal;
                        results[index][1] = String.valueOf(votes);
                        index++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return results;
    }
}


Survey.html: HTML form containing radio buttons for selecting a favorite animal and a submit button. SurveyServlet.java: Servlet to handle POST requests. It updates the total number of votes for the selected animal in the database and generates a dynamically generated XHTML document containing the survey results.