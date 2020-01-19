import java.sql.*;
import java.util.*;

public class updateDatabase {
    private Connection connection;

    public updateDatabase(Connection connection){
        this.connection = connection;
    }
    public Connection getConnection(){
        return connection;
    }
    public int insertSensorInfo(int light, int soilMoist, int CO2, int tVoc, int rain){
        int numOfRowsUpdated = 0;
        while(numOfRowsUpdated != 60){
            try {
                String query = "INSERT INTO sensor VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, light);
                statement.setInt(2, soilMoist);
                statement.setInt(3, CO2);
                statement.setInt(4, tVoc);
                statement.setInt(5, rain);
    
                numOfRowsUpdated++;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}