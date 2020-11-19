package pm;

import beans.CoursBean;
import beans.EtudiantBean;
import beans.InscriptionBean;

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
    public List<T> retrieveSet(Class<T> beanClass, String sql) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Créer une classe qui :
            // - Vérifier annotation présente
            // - Vérifier les champs
    }
    public void createConnection(){


    }

    public void insert(T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }
}
