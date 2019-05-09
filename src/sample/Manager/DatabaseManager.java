package sample.Manager;

import sample.Model.Robot;
import sample.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    private static DatabaseManager single_instance = null;

    public User currentUser = null;

    private DatabaseManager() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        String url = "jdbc:mysql://localhost:3306/MTDLProject?serverTimezone=UTC";

        connection = DriverManager.getConnection(url, "root", "");
    }

    public static DatabaseManager getInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if(single_instance == null) {
            single_instance = new DatabaseManager();
        }

        return single_instance;
    }

    public List<User> selectAllUsers() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");
            ResultSet resultSet = statement.executeQuery();
            List<User> user = new ArrayList<User>();

            while(resultSet.next()) {
                 int id = resultSet.getInt("user_id");
                 String name = resultSet.getString("name");
                 String role = resultSet.getString("role");
                 String password = resultSet.getString("password");
                 String email = resultSet.getString("email");
                 int no_robots = resultSet.getInt("no_robots");

                 User u = new User(id, name, role, password, email, no_robots);

                 user.add(u);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    public List<Robot> selectAllRobots() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Robot WHERE user_id = ?");
            statement.setInt(1, currentUser.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Robot> robots = new ArrayList<Robot>();

            while(resultSet.next()) {
                int robot_id = resultSet.getInt("robot_id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String state = resultSet.getString("state");
                int coordX = resultSet.getInt("coordX");
                int coordY = resultSet.getInt("coordY");
                String type = resultSet.getString("type");
                String icon = resultSet.getString("icon");
                String image = resultSet.getString("image");
                String connectivite = resultSet.getString("connectivite");

                Robot r = new Robot(robot_id, user_id, name, state, coordX, coordY, type, icon, image, connectivite);

                robots.add(r);
            }

            return robots;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Robot>();
        }
    }

    public void insertNewUser(String username, String password, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO User (name, role, password, email, no_robots) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, username);
        statement.setString(2, "admin");
        statement.setString(3, password);
        statement.setString(4, email);
        statement.setInt(5, 0);
        statement.execute();
    }
}
