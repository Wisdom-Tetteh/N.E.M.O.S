/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//import nemos_icud.Nemos_User;

/**
 *
 * @author admin
 */
public class SignUpNemos implements LoginNemos {

    private Connection connnection;

    public boolean insert(Nemos_User user) {
        try {
            String query = "INSERT INTO users values ('" + user.getUserName() + "','" + user.getName() + "','" + user.getLastName() + "','" + user.getPassword() + "')";
            Statement statement = this.connnection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Nemos_User getUser(String userName, String password) {
        try {
            String query = "SELECT * FROM users where username = '" + userName + "' and password = '" + password + "'";

            PreparedStatement preparedStatement = this.connnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            Nemos_User user = null;

            if (resultSet.next()) {
                user = new Nemos_User();
                user.setUserName(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPassword(resultSet.getString("password"));
            }
            preparedStatement.close();
            resultSet.close();
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connnection = DriverManager.getConnection("jdbc:mysql://localhost/java_db", "root", "");
            System.out.println("Connection to Server Successful.");
            JOptionPane.showMessageDialog(null,"Connection to Server Successful","Server Status",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null,"Unable to establish connection!! Try Again","Server Status",JOptionPane.ERROR_MESSAGE);
        }
    }
}
