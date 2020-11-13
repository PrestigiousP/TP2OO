import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            // 1. Get a connection to database
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TP2OO", "postgres", "196432");
            // 2. Create a statement
            Statement statement = connect.createStatement();
            // 3. Execute SQL query
            ResultSet myRs = statement.executeQuery("select * from cours");
            // 4. Process the result set
            while(myRs.next()){
                System.out.println(myRs.getString("name") +  ", " + myRs.getString("coursid"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}