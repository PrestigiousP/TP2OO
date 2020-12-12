package pm;

import pm.annotations.FK;
import pm.annotations.PK;
import pm.retrieve.RAnnotationsProcessor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentManager<T> {         //Objectif : effectuer les requêtes demandées par rapport à la db vers les beans

    private final Connection connection;

    public PersistentManager() throws SQLException {
        // 1. Get a connection to database
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBTP2", "postgres", "nascar466");
    }

    public List<T> retrieveSet(Class<T> tClass, String sqlRequest) {

        RAnnotationsProcessor retrieveProcessor = new RAnnotationsProcessor(tClass);
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

                    List<T> colInnerBean = retrieveSet(representCol, sql);

                    outerField.setAccessible(true);
                    outerField.set(tObject, colInnerBean);
                }

                int iCounter = 0;
                for (InnerBeans i : (List<InnerBeans>) retrieveProcessor.getInnerBeansFields()){

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

    public<T> int bulkInsert(List<T> b) throws SQLException, IllegalAccessException {
        for(T bean : b){
            this.insert(bean);
        }
        return 1;
    }

    public<T> int insert(T b) throws SQLException, IllegalAccessException {
        int i = 0;
        RAnnotationsProcessor insertProcessor = new RAnnotationsProcessor(b.getClass());
        String sqlInsert = "INSERT INTO " + insertProcessor.getDbAnnotations().table() + "(";
        String sqlInconnu = "(";
        List<Field> simpleFields = insertProcessor.getSimpleFields();
        String typeField[] = new String[100];
        for (i = 0; i < simpleFields.size(); i++) {
            Field fieldT = simpleFields.get(i);
            typeField[i] = fieldT.getType().getTypeName();
            fieldT.setAccessible(true);
            //Nom du champ ajouté à notre sqlInsert
            sqlInsert += fieldT.getName();
            if (i == simpleFields.size()-1){
                sqlInconnu += "?)";
                sqlInsert+=")";
            }
            else{
                sqlInsert +=", ";
                sqlInconnu += "?, ";
            }
        }
        sqlInsert += " VALUES " + sqlInconnu;
        PreparedStatement ps = connection.prepareStatement(sqlInsert);
        for (i = 0; i < simpleFields.size(); i++) {
            switch (typeField[i]) {
                case "java.lang.String":
                    simpleFields.get(i).setAccessible(true);
                    ps.setString((i+1), (String) simpleFields.get(i).get(b));
                    break;
                case "int":
                    simpleFields.get(i).setAccessible(true);
                    ps.setInt((i+1), (int) simpleFields.get(i).get(b));
                    break;
            }
        }
        ps.execute();

       List<InnerBeans> innerBean = insertProcessor.getInnerBeansFields();
        for (i = 0; i < innerBean.size(); i++) {
            innerBean.get(i).getOuterField().setAccessible(true);
            this.insert(innerBean.get(i).getOuterField().get(b));
        }

        List<CollectionInnerBeans> colInnerBean = insertProcessor.getCollectionInnerBeansFields();
        for (i = 0; i < colInnerBean.size(); i++) {
            colInnerBean.get(i).getOuterField().setAccessible(true);
            List<T> colInner = (List<T>) colInnerBean.get(i).getOuterField().get(b);
            this.bulkInsert(colInner);
        }

        return 1;
    }
}