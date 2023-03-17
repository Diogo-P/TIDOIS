import java.util.ArrayList;
public class TaskDAO {
    private Connection connection;
    
    public TaskDAO(Connection connection) {
      this.connection = connection;
    }
    
    public void insert(Task task) throws SQLException {
      // implementação para inserir uma tarefa no banco de dados
      String sql = "INSERT INTO tasks (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, task.getTitle());
        stmt.setString(2, task.getDescription());
        stmt.setDate(3, Date.valueOf(task.getDueDate()));
        stmt.setString(4, task.getStatus());
        stmt.executeUpdate();
    }
    }
    
    public void update(Task task) throws SQLException {
      // implementação para atualizar uma tarefa no banco de dados
      String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
      try (PreparedStatement stmt = connection.prepareStatement(sql)) {
          stmt.setString(1, task.getTitle());
          stmt.setString(2, task.getDescription());
          stmt.setDate(3, Date.valueOf(task.getDueDate()));
          stmt.setString(4, task.getStatus());
          stmt.setInt(5, task.getId());
          stmt.executeUpdate();
      }
    }
    
    public void delete(int taskId) throws SQLException {
      // implementação para excluir uma tarefa do banco de dados
      String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, taskId);
        stmt.executeUpdate();
    }
    }
    
    public List<Task> selectAll() throws SQLException {
      // implementação para selecionar todas as tarefas do banco de dados
      String sql = "SELECT id, title, description, due_date, status FROM tasks ORDER BY id";
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setDueDate(rs.getDate("due_date").toLocalDate());
            task.setStatus(rs.getString("status"));
            tasks.add(task);
        }
    }
    return tasks;
    }
    
    public Task select(int taskId) throws SQLException {
      // implementação para selecionar uma tarefa pelo ID no banco de dados
      public Task select(int taskId) throws SQLException {
        String sql = "SELECT id, title, description, due_date, status FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setDueDate(rs.getDate("due_date").toLocalDate());
                    task.setStatus(rs.getString("status"));
                    return task;
                } else {
                    return null;
                }
            }
        }
    }
  }