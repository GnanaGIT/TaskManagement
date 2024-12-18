import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/taskdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void addTask(Task task) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "INSERT INTO tasks (title, description, priority, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, task.getTitle());
        stmt.setString(2, task.getDescription());
        stmt.setString(3, task.getPriority());
        stmt.setString(4, task.getStatus());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Task> getAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM tasks";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setPriority(rs.getString("priority"));
            task.setStatus(rs.getString("status"));
            tasks.add(task);
        }
        conn.close();
        return tasks;
    }
}
