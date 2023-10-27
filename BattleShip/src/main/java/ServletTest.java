

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.json.simple.*;



@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> jsonData = new HashMap<>();
        String str = "hello";
        jsonData.put(str, str);

        // Read the JSON data from the request's input stream
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Print the received JSON data
        System.out.println("(GET) Received JSON data: " + requestBody.toString());

        // Set content type and character encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send the response data
        response.getWriter().write(jsonData.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read the JSON data from the request's input stream
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Print the received JSON data
        System.out.println("Received JSON data: " + requestBody.toString());
        String responseData = "200";

        // Set content type and character encoding
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Send the response data
        response.getWriter().write(responseData);
    }
}







