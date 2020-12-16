package pm;

import pm.annotations.DbJoin;
import pm.retrieve.RAnnotationsProcessor;
import java.lang.reflect.Field;

public class CollectionInnerBeans {
    private Field outerField;
    private RAnnotationsProcessor retrieveProcessor;    //récuper les champs du innerbean (récursif)
    private DbJoin joinAnnotation;                     //lecture a partir du bean

    public CollectionInnerBeans(Field outerField, RAnnotationsProcessor retrieveProcessor, DbJoin joinAnnotation) {
        this.outerField = outerField;
        this.retrieveProcessor = retrieveProcessor;
        this.joinAnnotation = joinAnnotation;
    }

    public Field getOuterField() {
        return outerField;
    }

    public void setOuterField(Field outerField) {
        this.outerField = outerField;
    }

    public RAnnotationsProcessor getRetrieveProcessor() {
        return retrieveProcessor;
    }

    public void setRetrieveProcessor(RAnnotationsProcessor retrieveProcessor) {
        this.retrieveProcessor = retrieveProcessor;
    }

    public DbJoin getJoinAnnotation() {
        return joinAnnotation;
    }

    public void setJoinAnnotation(DbJoin joinAnnotation) {
        this.joinAnnotation = joinAnnotation;
    }
}
