package pm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //Peut être utilisée sur les champs
@Retention(RetentionPolicy.RUNTIME)     //Quand est-ce qu'elle sera utilisée
public @interface DbJoinInner {
    String[] innerkeys() default "";
    String[] outerkeys() default "";
}
