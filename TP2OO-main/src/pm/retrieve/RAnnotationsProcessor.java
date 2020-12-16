package pm.retrieve;

import pm.CollectionInnerBeans;
import pm.InnerBeans;
import pm.annotations.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

//Un type peut avoir un champ simple, un bean, ou une collection de beans
public class RAnnotationsProcessor<T> {        //Récupérer les métats informations pour prép au retrieve
    private List<Field> simpleFields;
    private List<Field> ignoredFields;
    private List<InnerBeans> innerBeansFields;
    private List<CollectionInnerBeans> collectionInnerBeansFields;
    private DbEntity dbAnnotations;

    //Constructeur
    public RAnnotationsProcessor(Class<T> tClass) {
        this.simpleFields = new ArrayList<Field>();
        this.ignoredFields = new ArrayList<Field>();
        this.innerBeansFields = new ArrayList<InnerBeans>();
        this.collectionInnerBeansFields = new ArrayList<CollectionInnerBeans>();

        for (Field fieldT : tClass.getDeclaredFields()){
            //Liste de champs à ignorer lors du retrieve
            if(fieldT.isAnnotationPresent(DbIgnored.class)){
                this.ignoredFields.add(fieldT);             //On ne s'en servira probablement pas, mais on les gardes en mémoire si jamais.
            }
            //Liste de collectionInnerBean sur lesquels on doit effectuer un retrieve
            else if(fieldT.isAnnotationPresent(DbJoin.class)){
                //Représentant de la classe
                ParameterizedType stringListType = (ParameterizedType) fieldT.getGenericType();
                Class<T> representCol = (Class<T>) stringListType.getActualTypeArguments()[0];
                //Ajout de la collectionInnerBean dans notre liste
                this.collectionInnerBeansFields.add(new CollectionInnerBeans(fieldT, new RAnnotationsProcessor(representCol), fieldT.getAnnotation(DbJoin.class)));
            }
            //Liste de InnerBean sur lesquels on doit effectuer un retrieve
            else if(fieldT.isAnnotationPresent(DbJoinInner.class)){
                //Représent de la classe
                Class<T> representInner = (Class<T>) fieldT.getType();
                //Ajout du InnerBean dans notre liste
                this.innerBeansFields.add(new InnerBeans(fieldT, new RAnnotationsProcessor(representInner), fieldT.getAnnotation(DbJoinInner.class)));
            }
            //Ajout des champs de types simples dans notre liste
            else{
                this.simpleFields.add(fieldT);
            }
        }
        //Annotation de la table associée à notre bean
        this.dbAnnotations = tClass.getAnnotation(DbEntity.class);
    }

    //Getter and setter
    public List<Field> getSimpleFields() {
        return simpleFields;
    }

    public void setSimpleFields(List<Field> simpleFields) {
        this.simpleFields = simpleFields;
    }

    public List<InnerBeans> getInnerBeansFields() {
        return innerBeansFields;
    }

    public void setInnerBeansFields(List<InnerBeans> innerBeansFields) {
        this.innerBeansFields = innerBeansFields;
    }

    public List<CollectionInnerBeans> getCollectionInnerBeansFields() {
        return collectionInnerBeansFields;
    }

    public void setCollectionInnerBeansFields(List<CollectionInnerBeans> collectionInnerBeansFields) {
        this.collectionInnerBeansFields = collectionInnerBeansFields;
    }

    public DbEntity getDbAnnotations() {
        return dbAnnotations;
    }

    public void setDbAnnotations(DbEntity dbAnnotations) {
        this.dbAnnotations = dbAnnotations;
    }
}
