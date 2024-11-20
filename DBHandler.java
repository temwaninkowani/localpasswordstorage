import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {


public List<Object[]> retrievepasswords(){

       List<Object[]> passwordlist = new ArrayList<>();
       String query = "SELECT use, password FROM userdata";
      try{
         Class.forName("org.sqlite.JDBC");
         try(Connection connection = DriverManager.getConnection(generate.DB_URL);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)){

          while(resultSet.next()){
             String use = resultSet.getString("use");
             String password = resultSet.getString("password");
             passwordlist.add(new Object[]{use,password});
          }
      }
          
       } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        System.out.print("Error retriving passwords");
       }

       return passwordlist;
    }


    

}
