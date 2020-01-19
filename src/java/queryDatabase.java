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
    public List<Integer> getSensorData(){
        List<Integer> result = new ArrayList<Integer>();

        try{
            String query = "SELECT * FROM sensor WHERE loggedat = (SELECT max(loggedat) FROM sensor)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while(resultSet.next()){
                result.add(resultSet.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    } 
}