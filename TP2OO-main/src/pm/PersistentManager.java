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

    public List<T> retrieveSet(Class<T> tClass, String sqlRequest) {

        List<T> listTObject = new ArrayList<>();
        T tObject;
        int valuePk = 0;
        int counter = 0;
        int valueFk[] = new int[10];
        RAnnotationsProcessor Rannotation = new RAnnotationsProcessor(tClass);

        try{
            listTObject = new ArrayList<>();
            Field[] fields = tClass.getDeclaredFields();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);

            while (resultSet.next()) {
                tObject = tClass.newInstance();

                for (Field fieldT : (List<Field>) Rannotation.getSimpleFields()) {
                    fieldT.setAccessible(true);
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

                for (CollectionInnerBeans c : (List<CollectionInnerBeans>) Rannotation.getCollectionInnerBeansFields()){

                    Field outerField = c.getOuterField();
                    ParameterizedType stringListType = (ParameterizedType) c.getOuterField().getGenericType();
                    Class<T> representCol = (Class<T>) stringListType.getActualTypeArguments()[0];
                    String sql = "SELECT * FROM " + c.getRetrieveProcessor().getDbAnnotations().table() + " WHERE " + c.getJoinAnnotation().innerkeys()[0] + " = " + valuePk;

                    List<T> colInnerBean = retrieveSet(representCol, sql);

                    outerField.setAccessible(true);
                    outerField.set(tObject, colInnerBean);
                }

                int iCounter = 0;
                for (InnerBeans i : (List<InnerBeans>) Rannotation.getInnerBeansFields()){

                    Field outerField = i.getOuterField();
                    Class<T> representCol = (Class<T>) i.getOuterField().getType(); //SELECT * FROM cours WHERE coursid = 1
                    String sql = "SELECT * FROM " + i.getRetrieveProcessor().getDbAnnotations().table() + " WHERE " + i.getJoinAnnotation().innerkeys()[0] + " = " + valueFk[iCounter++];

                    List<T> InnerBean = retrieveSet(representCol, sql);

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


/*
*
* System.out.println("-----------------------------------------------");
                    DbJoin annotation = fieldT.getAnnotation(DbJoin.class);
//                    tObject.getClass().getDeclaredField();

                    if (annotation != null) {
                        //fieldT.getGenericType().toString().startsWith("java.util.list")

                        if(fieldT.getGenericType().getTypeName().startsWith("java.util.List")){
                            ParameterizedType stringListType = (ParameterizedType) fieldT.getGenericType();
                            Class<T> collectionInnerBeans = (Class<T>) stringListType.getActualTypeArguments()[0];
                            DbEntity dbEntity = collectionInnerBeans.getAnnotation(DbEntity.class);
                            //Get le field de l'objet actuel.
                            Field field = tObject.getClass().getDeclaredField(annotation.innerkeys()[0]);
                            field.setAccessible(true);
                            //Get la valeur du champ fieldT de la Classe spécifié dans retrieveSet
                            Object value = field.get(tObject);

                            String s = "SELECT * FROM "+ dbEntity.table() +
                                    " WHERE " + annotation.innerkeys()[0] +"=" + value;
                            System.out.println(s);

                            fieldT.set(tObject, this.retrieveSet(collectionInnerBeans, s));
                        }
                        else{
                            String str = fieldT.getType().getTypeName();

                            Class<T> classT = (Class<T>) Class.forName(str);
                            DbEntity dbEntity = classT.getAnnotation(DbEntity.class);

                            System.out.println(dbEntity.table());
//                            String s = "SELECT * FROM "+ dbEntity.table() +
//                                    " WHERE " +  +"=" + value;
                            //Get le field de l'objet actuel.
                           // Field fieldO = tObject.getClass().getDeclaredField(annotation.innerkeys()[0]);


                        }

                    } else {


                        fieldRA.setAccessible(true);
                        switch(typeField) {
                                case "java.lang.String":
                                    fieldRA.set(tObject, resultSet.getString(nameField));
                                break;
                            case "int":
                                fieldRA.set(tObject, resultSet.getInt(nameField));
                                break;

                        }
                        System.out.println("-----------------------------------------------");

                    }*/