import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewTaskServlet")
public class ViewTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (Connection connection = DBConnection.getConnection();
             PrintWriter out = response.getWriter()) {
            
            String sql = "SELECT * FROM tasks";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            out.println("<html><body>");
            out.println("<h2>Task List</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Title</th><th>Description</th></tr>");
            
            while (resultSet.next()) {
                out.println("<tr>");
                out.println("<td>" + resultSet.getInt("id") + "</td>");
                out.println("<td>" + resultSet.getString("title") + "</td>");
                out.println("<td>" + resultSet.getString("description") + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error retrieving tasks: " + e.getMessage());
        }
    }
}
