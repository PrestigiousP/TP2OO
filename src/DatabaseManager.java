import java.beans.beancontext.BeanContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager<T> {

    private Connection connection;

    public DatabaseManager() throws SQLException {
        // 1. Get a connection to database
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TP2OO", "postgres", "196432");
    }

    public List<T> retrieve(Class<T> t, String sql) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        t.getClass();
        Method method = t.getDeclaredMethod("getTableName", null);
        String tableName = (String) method.invoke(t.getClass());
        ResultSet resultSet = statement.executeQuery("select * from "+ tableName);

        if (tableName == "cours") {
            while(resultSet.next()){
                CoursBean c = new CoursBean();
                t.getClass().getDeclaredMethod("setCoursID");
                c.setCoursID(resultSet.getInt("coursid"));
                c.setName(resultSet.getString("name"));
                c.setName(resultSet.getString("sigle"));
                c.setName(resultSet.getString("description"));
                list.add(t.cast(c));
            }
        }
        else if (tableName == "etudiant"){
            while(resultSet.next()){
                EtudiantBean c = new EtudiantBean();
                c.setEtudiantid(resultSet.getInt("etudiantid"));
                c.setFname(resultSet.getString("fname"));
                c.setLname(resultSet.getString("lname"));
                c.setAge(resultSet.getInt("age"));
                list.add(t.cast(c));
            }
        }
        else if (tableName == "inscription"){
            while(resultSet.next()){
                InscriptionBean c = new InscriptionBean();
                c.setInscriptionid(resultSet.getInt("inscriptionid"));
                c.setEtudiantid(resultSet.getInt("etudiantid"));
                c.setCoursid(resultSet.getInt("coursid"));
                list.add(t.cast(c));
            }
        }
        return list;
    }
    public void createConnection(){


    }

    public void insert(T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = t.getClass().getDeclaredMethod("getTableName", null);
        String table = (String) method.invoke(t);
        method = t.getClass().getDeclaredMethod("sqlQuery", String.class);
        String query = (String) method.invoke("invert");
        PreparedStatement ps = null;

        if(table == "cours") {
            // Create the sql insert preparedstatement
            ps = connection.prepareStatement(query);
            ps.setInt(1, 12);
            method = t.getClass().getDeclaredMethod("getName", null);
            ps.setString(2, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getSigle", null);
            ps.setString(3, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getDescription", null);
            ps.setString(4, (String) method.invoke(t));
        }
        else if (table == "etudiant"){
//             query = "insert into etudiant" + " (etudiantid, fname, lname, age)"
//                    + " values (?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, 11);
            method = t.getClass().getDeclaredMethod("getFname", null);
            ps.setString(2, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getLname", null);
            ps.setString(3, (String) method.invoke(t));
            method = t.getClass().getDeclaredMethod("getAge", null);
            ps.setInt(4, (int) method.invoke(t));
        }
        else if (table == "inscription"){
//            query = "insert into inscription" + " (inscriptionid, etudiantid, coursid, date)"
//                    + " values (?, ?, ?, ?)";
            // Create the sql insert preparedstatement
            ps = connection.prepareStatement(query);
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
