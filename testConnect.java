import java.sql.Connection;
import java.sql.DriverManager;

public class testConnect {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:userdata.db")) {
            if (conn != null) {
                System.out.println("Connection successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

