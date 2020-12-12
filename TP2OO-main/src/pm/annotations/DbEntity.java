package pm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //Peut être utilisée sur les classes
@Retention(RetentionPolicy.RUNTIME)     //Quand est-ce qu'elle sera utilisée
public @interface DbEntity {
    String table();         //Spécifie la table de la Db
}
