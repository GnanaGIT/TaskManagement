import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taskId = request.getParameter("taskId");
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(taskId));
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                response.getWriter().write("Task deleted successfully!");
            } else {
                response.getWriter().write("Task not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error deleting task: " + e.getMessage());
        }
    }
}
