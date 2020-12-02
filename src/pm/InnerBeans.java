package pm;

import pm.retrieve.RAnnotationsProcessor;

import java.lang.reflect.Field;

public class InnerBeans {
    private Field outerField;
    private RAnnotationsProcessor retrieveProcessor;    //récuper les champs du innerbean (récursif)

}
