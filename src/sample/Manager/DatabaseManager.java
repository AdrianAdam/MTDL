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

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                ("UPDATE User SET name=?, role=?, password=?, email=?, no_robots=? WHERE user_id=?");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getRole());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getNoRobots());
        statement.setInt(6, user.getId());
        statement.execute();

        currentUser = user;
    }

    public List<Robot> selectAllRobots() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Robot WHERE user_id = ?");
            statement.setInt(1, currentUser.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Robot> robots = new ArrayList<Robot>();

            while(resultSet.next()) {
                robots.add(getRobotDetails(resultSet));
            }

            return robots;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Robot>();
        }
    }

    public void updateRobot(Robot robot) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                ("UPDATE Robot SET name=?, state=?, coordX=?, coordY=?, type=?, icon=?, image=?, connectivite=? WHERE robot_id=?");
        statement.setString(1, robot.getName());
        statement.setString(2, robot.getState());
        statement.setInt(3, robot.getCoordX());
        statement.setInt(4, robot.getCoordY());
        statement.setString(5, robot.getType());
        statement.setString(6, robot.getIcon());
        statement.setString(7, robot.getImage());
        statement.setString(8, robot.getConnectivity());
        statement.setInt(9, robot.getRobot_id());
        statement.execute();
    }

    public void insertNewRobot(String name, String state, int coordX, int coordY, String type, String icon, String image, String connectivity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO Robot (user_id, name, state, coordX, coordY, type, icon, image, connectivite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, currentUser.getId());
        statement.setString(2, name);
        statement.setString(3, state);
        statement.setInt(4, coordX);
        statement.setInt(5, coordY);
        statement.setString(6, type);
        statement.setString(7, icon);
        statement.setString(8, image);
        statement.setString(9, connectivity);
        statement.execute();
    }

    public void deleteRobot(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Robot WHERE name=?");
        statement.setString(1, name);
        statement.execute();
    }

    public Robot selectOneRobot(String robot_name) throws SQLException {
        Robot robot = new Robot();

        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Robot WHERE name=?");
        statement.setString(1, robot_name);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            robot = getRobotDetails(resultSet);
        }

        return robot;
    }

    public Robot getRobotDetails(ResultSet resultSet) throws SQLException {
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

        return r;
    }
}
