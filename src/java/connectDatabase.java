import java.sql.*;
import java.io.*;
import java.util.*;

public class connectDatabase{
    public static void main(String[] args){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://35.224.80.243/postgres",
                                                     "postgres", 
                                                     "nanny");
            if (connection != null){
                System.out.println("Connected to the cloud database!");
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error while connection to the cloud database: " + e);
            e.printStackTrace();
        } finally {
            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    System.out.println("Failed to close connection to cloud database: " + e);
                    e.printStackTrace();
                }
            }
        }
    }
}
