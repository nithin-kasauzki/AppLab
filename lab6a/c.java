c)
<!DOCTYPE html>
<html>
<head>
    <title>Employee Information Form</title>
</head>
<body>
    <h2>Employee Information Form</h2>
    <form action="EmployeeServlet" method="post">
        <label for="empName">Employee Name:</label>
        <input type="text" id="empName" name="empName"><br><br>
        
        <label for="empID">Employee ID:</label>
        <input type="text" id="empID" name="empID"><br><br>
        
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob"><br><br>
        
        <label for="department">Department:</label>
        <input type="text" id="department" name="department"><br><br>
        
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary"><br><br>
        
        <label for="email">Email ID:</label>
        <input type="email" id="email" name="email"><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class EmployeeServlet extends HttpServlet {
    public void service(ServletRequest request, ServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Form Parameters</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Form Parameters and Values:</h2>");
        
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            out.println("<p>" + paramName + ": " + paramValue + "</p>");
        }
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
