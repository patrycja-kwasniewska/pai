/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author patrycjakwasniewska
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Klasa {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "Naczyniachłonne01!"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Połączenie z bazą danych udało się!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}