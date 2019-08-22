package driver;

import java.sql.*;

import java.util.Scanner;

public class Driver {

    private static String user = "root";

    private static Scanner scan;

    static {
        scan = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("Starting MySQL...");
        System.out.println("Default user: " + user);

        System.out.println("Change user? (Y/n)");
        if (scan.nextLine().toUpperCase().equals("Y")) {
            System.out.print("Enter new username: ");
            user = scan.nextLine();
        }

        System.out.print("Enter password for " + user + ": ");
        String password = scan.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", user, password)) {
            Thread t = new Thread(new Query(connection, "SELECT * FROM country"));

            t.start();
            t.join();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
