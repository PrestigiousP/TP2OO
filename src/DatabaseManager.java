import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class DatabaseManager<T> {

    private T t;
    private String table;
    private Connection connection;
    //private Statement statement;
    private ResultSet rs;

    public DatabaseManager(T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        this.t = t;
        Method method = t.getClass().getDeclaredMethod("getTableName", null);
        table = (String) method.invoke(t);
        System.out.println("la table est : " + table);
        // 1. Get a connection to database
        Connection connect = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/TP2OO", "postgres", "196432");
        this.connection = connect;
        // 2. Create a statement
        Statement statement = connect.createStatement();
        // 3. Execute SQL query
        ResultSet resultSet = statement.executeQuery("select * from "+ table);
        this.rs = resultSet;
//        while(rs.next()){
//            //StringBuffer bf = new StringBuffer();
//            System.out.println("ID" + rs.getInt("coursid"));
//            System.out.println("Name" + rs.getString("name"));
//            System.out.println("sigle" + rs.getString("sigle"));
//        }
    }

    public void insert() throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String query = "";
        PreparedStatement ps = null;

        if(table == "cours") {
             query = "insert into cours" + " (coursid, name, sigle, description)"
                    + " values (?, ?, ?, ?)";
            // Create the sql insert preparedstatement
            ps = connection.prepareStatement(query);
            Method method;
            ps.setInt(1, 11);
            method = t.getClass().getDeclaredMethod("getName", null);
            ps.setString(2, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getSigle", null);
            ps.setString(3, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getDescription", null);
            ps.setString(4, (String) method.invoke(t));
        }
        else if (table == "etudiant"){
             query = "insert into etudiant" + " (etudiantid, fname, lname, age)"
                    + " values (?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            Method method;
            ps.setInt(1, 11);
            method = t.getClass().getDeclaredMethod("getFname", null);
            ps.setString(2, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getLname", null);
            ps.setString(3, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getAge", null);
            ps.setInt(4, (int) method.invoke(t));
        }
        else if (table == "inscription"){
            query = "insert into inscription" + " (inscriptionid, etudiantid, coursid, date)"
                    + " values (?, ?, ?, ?)";
            // Create the sql insert preparedstatement
            ps = connection.prepareStatement(query);
            Method method;
            ps.setInt(1, 11);
            method = t.getClass().getDeclaredMethod("getFname", null);
            ps.setString(2, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getLname", null);
            ps.setString(3, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getAge", null);
            ps.setString(4, (String) method.invoke(t));
        }



        // Exécute le PreparedStatement
        ps.execute();

        connection.close();
    }
}
