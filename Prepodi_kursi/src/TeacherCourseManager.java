import java.sql.*;

public class TeacherCourseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;


    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void addTeacher(String fullName, int age) {
        try {
            String query = "INSERT INTO teachers (full_name, age) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, fullName);
                ps.setInt(2, age);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Преподаватель добавлен с ID: " + rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addCourse(String name, int hours) {
        try {
            String query = "INSERT INTO courses (name, hours) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.setInt(2, hours);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Курс добавлен с ID: " + rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void assignCourseToTeacher(int teacherId, int courseId) {
        try {
            String query = "INSERT INTO teacher_courses (teacher_id, course_id) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, teacherId);
                ps.setInt(2, courseId);
                ps.executeUpdate();
                System.out.println("Курс назначен преподавателю.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void removeCourseFromTeacher(int teacherId, int courseId) {
        try {
            String query = "DELETE FROM teacher_courses WHERE teacher_id = ? AND course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, teacherId);
                ps.setInt(2, courseId);
                ps.executeUpdate();
                System.out.println("Курс удален у преподавателя.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateTeacher(int teacherId, String fullName, int age) {
        try {
            String query = "UPDATE teachers SET full_name = ?, age = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, fullName);
                ps.setInt(2, age);
                ps.setInt(3, teacherId);
                ps.executeUpdate();
                System.out.println("Данные преподавателя обновлены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Обновить данные курса
    public static void updateCourse(int courseId, String name, int hours) {
        try {
            String query = "UPDATE courses SET name = ?, hours = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setInt(2, hours);
                ps.setInt(3, courseId);
                ps.executeUpdate();
                System.out.println("Данные курса обновлены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showTeachersAndCourses() {
        try {
            String query = "SELECT t.id, t.full_name, t.age, c.name AS course_name " +
                    "FROM teachers t " +
                    "JOIN teacher_courses tc ON t.id = tc.teacher_id " +
                    "JOIN courses c ON tc.course_id = c.id";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("Преподаватель ID: " + rs.getInt("id") +
                            ", ФИО: " + rs.getString("full_name") +
                            ", Возраст: " + rs.getInt("age") +
                            ", Курс: " + rs.getString("course_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showCourses() {
        try {
            String query = "SELECT * FROM courses";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("Курс ID: " + rs.getInt("id") +
                            ", Название: " + rs.getString("name") +
                            ", Часы: " + rs.getInt("hours"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showTeachers() {
        try {
            String query = "SELECT * FROM teachers";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("Преподаватель ID: " + rs.getInt("id") +
                            ", ФИО: " + rs.getString("full_name") +
                            ", Возраст: " + rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}