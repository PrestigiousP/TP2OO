package pm.retrieve;

import pm.CollectionInnerBeans;
import pm.InnerBeans;
import pm.annotations.DbEntity;

import java.lang.reflect.Field;
import java.util.List;

public class RAnnotationsProcessor {        //Récupérer les métats informations pour prép au retrieve
    private List<Field> simpleFields;
    private List<InnerBeans> innerBeansFields;
    private List<CollectionInnerBeans> collectionInnerBeansFields;
    private DbEntity dbAnnotations;
}
