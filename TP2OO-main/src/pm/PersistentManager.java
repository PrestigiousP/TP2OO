package pm;

import pm.annotations.DbEntity;
import pm.annotations.DbJoin;
import pm.annotations.FK;
import pm.annotations.PK;
import pm.retrieve.RAnnotationsProcessor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistentManager<T> {         //Objectif : effectuer les requêtes demandées par rapport à la db vers les beans

    private final Connection connection;

    public PersistentManager() throws SQLException {
        // 1. Get a connection to database
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBTP2", "postgres", "nascar466");
    }

    public List<T> retrieveSet(Class<T> tClass, String sqlRequest, RAnnotationsProcessor retrieveProcessor) {

        List<T> listTObject = new ArrayList<>();
        T tObject;
        int valuePk = 0;
        int counter = 0;
        int valueFk[] = new int[10];

        try{
            listTObject = new ArrayList<>();
            Field[] fields = tClass.getDeclaredFields();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);

            while (resultSet.next()) {
                tObject = tClass.newInstance();


                for (Field fieldT : (List<Field>) retrieveProcessor.getSimpleFields()) {
                    fieldT.setAccessible(true);
                    //Nom du champ et nom du représentant de la classe associée à notre champ de type simple
                    String nameField = fieldT.getName();
                    String typeField = fieldT.getType().getTypeName();

                    switch (typeField) {
                        case "java.lang.String":
                            fieldT.set(tObject, resultSet.getString(nameField));
                            break;
                        case "int":
                            fieldT.set(tObject, resultSet.getInt(nameField));
                            break;
                    }

                    if(fieldT.isAnnotationPresent(PK.class)){
                        valuePk = (int) fieldT.get(tObject);
                    }
                    if(fieldT.isAnnotationPresent(FK.class)){
                        valueFk[counter++] = (int) fieldT.get(tObject);
                    }
                }

                for (CollectionInnerBeans c : (List<CollectionInnerBeans>) retrieveProcessor.getCollectionInnerBeansFields()){
                    //Représentant du champ outerField
                    Field outerField = c.getOuterField();

                    //Représentant de la classe de notre champ de collectionInnerBean "outerField"
                    ParameterizedType stringListType = (ParameterizedType) outerField.getGenericType();
                    Class<T> representCol = (Class<T>) stringListType.getActualTypeArguments()[0];

                    //Requête SQL
                    String sql = "SELECT * FROM " + c.getRetrieveProcessor().getDbAnnotations().table() + " WHERE " + c.getJoinAnnotation().innerkeys()[0] + " = " + valuePk;

                    List<T> colInnerBean = retrieveSet(representCol, sql, c.getRetrieveProcessor());

                    outerField.setAccessible(true);
                    outerField.set(tObject, colInnerBean);
                }

                int iCounter = 0;
                for (InnerBeans i : (List<InnerBeans>) retrieveProcessor.getInnerBeansFields()){

                    Field outerField = i.getOuterField();
                    Class<T> representCol = (Class<T>) i.getOuterField().getType(); //SELECT * FROM cours WHERE coursid = 1
                    String sql = "SELECT * FROM " + i.getRetrieveProcessor().getDbAnnotations().table() + " WHERE " + i.getJoinAnnotation().innerkeys()[0] + " = " + valueFk[iCounter++];

                    List<T> InnerBean = retrieveSet(representCol, sql, i.getRetrieveProcessor());

                    outerField.setAccessible(true);
                    outerField.set(tObject, InnerBean.get(0));
                }

                listTObject.add(tObject);
                
            }
            return listTObject;
        }
        catch(IllegalAccessException | SQLException | InstantiationException e){
            System.out.println(e);
        }
        return listTObject;
    }
}