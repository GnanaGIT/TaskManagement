<%@ page import="java.util.*, Task, TaskDAO" %>
<html>
<head><title>View Tasks</title></head>
<body>
    <h2>All Tasks</h2>
    <table>
        <tr>
            <th>ID</th><th>Title</th><th>Description</th><th>Priority</th><th>Status</th>
        </tr>
        <%
            TaskDAO dao = new TaskDAO();
            List<Task> tasks = dao.getAllTasks();
            for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getId() %></td>
            <td><%= task.getTitle() %></td>
            <td><%= task.getDescription() %></td>
            <td><%= task.getPriority() %></td>
            <td><%= task.getStatus() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
