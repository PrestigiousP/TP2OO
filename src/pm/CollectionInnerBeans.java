package pm;

import pm.annotations.DbJoin;
import pm.retrieve.RAnnotationsProcessor;

import java.lang.reflect.Field;

public class CollectionInnerBeans {
    private Field outerField;
    private RAnnotationsProcessor retrieveProcessor;    //récuper les champs du innerbean (récursif)
    private DbJoin joinAnnotation;                     //lecture a partir du bean
}
