import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taskId = request.getParameter("taskId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE tasks SET title = ?, description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, Integer.parseInt(taskId));
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                response.getWriter().write("Task updated successfully!");
            } else {
                response.getWriter().write("Task not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error updating task: " + e.getMessage());
        }
    }
}
