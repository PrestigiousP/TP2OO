package pm;

import beans.CoursBean;
import beans.EtudiantBean;
import beans.InscriptionBean;
import pm.annotations.DbEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentManager<T> {         //Objectif : effectuer les requêtes demandées par rapport à la db vers les beans

    private Connection connection;

    public PersistentManager() throws SQLException {
        // 1. Get a connection to database
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TP2OO", "postgres", "196432");
    }

    public List<T> retrieveSet(Class<T> tClass, String sqlRequest) {
        List<T> listTObject = null;
        T tObject;

        try {
            listTObject = new ArrayList<>();
            Field[] fields = tClass.getDeclaredFields();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);

            while (resultSet.next()) {
                tObject = tClass.newInstance();

                for (Field fieldT : fields) {
                    fieldT.setAccessible(true);
                    String nameField = fieldT.getName();
                    String typeField = fieldT.getType().getTypeName();
                    switch (typeField) {
                        case "java.lang.String":
                            fieldT.set(tObject, resultSet.getString(nameField));
                            break;
                        case "int":
                            fieldT.setInt(tObject, resultSet.getInt(nameField));
                            break;
                    }
                }

                listTObject.add(tObject);
            }
        } catch (InstantiationException | SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }






        return listTObject;

        //Créer une classe qui :
        // - Vérifier annotation présente
        // - Vérifier les champs

    }


//    public void insert(T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//
//    }
}
