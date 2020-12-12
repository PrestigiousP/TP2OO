package pm;

import pm.annotations.DbJoin;
import pm.annotations.DbJoinInner;
import pm.retrieve.RAnnotationsProcessor;

import java.lang.reflect.Field;

public class InnerBeans {
    private Field outerField; //par exemple dans inscription, y'a un field etudiant, ce field désignera donc le field etudiant
    private RAnnotationsProcessor retrieveProcessor;//récuper les champs du innerbean (récursif) processeur pour le field etudiant
    private DbJoinInner joinAnnotation;

    public InnerBeans(Field outerField, RAnnotationsProcessor retrieveProcessor, DbJoinInner joinAnnotation) {
        this.outerField = outerField;
        this.retrieveProcessor = retrieveProcessor;
        this.joinAnnotation = joinAnnotation;
    }

    public DbJoinInner getJoinAnnotation() {
        return joinAnnotation;
    }

    public void setJoinAnnotation(DbJoinInner joinAnnotation) {
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
}
