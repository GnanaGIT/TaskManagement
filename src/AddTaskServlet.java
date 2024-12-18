import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);

        try {
            TaskDAO dao = new TaskDAO();
            dao.addTask(task);
            response.sendRedirect("ViewTasksServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
