package gb.j2.chat.server.core;

import java.sql.*;

public class SqlClient {
    private static Connection connection = null;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:chatDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password) {
        String request = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(request)) {
            if (set.next()) {
                return set.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static void changeNickName(String oldNickName, String newNikcName){
        String request = String.format("update nickname from users where login='%s' and password='%s'");
        try{
            statement.execute(request);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    synchronized static void addNewUser(String login, String nickName, String password){
        String request = String.format("INSERT INTO users(login,  password, nickName) VALUES ('%s','%s','%s')", login, password, nickName);
        try{
            statement.execute(request);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    synchronized static StringBuilder getHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        String request = String.format("select nickname, post from history order by ID");
        return stringBuilder;
    }


}
