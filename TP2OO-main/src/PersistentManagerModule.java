import aspect.CheckAspect;
import aspect.CheckAspectInterface;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import pm.PersistentManager;
import pm.PersistentManagerLog;

import java.lang.annotation.Annotation;


public class PersistentManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        // binding matcher to the annotated method invocation
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(CheckAspectInterface.class),
                new CheckAspect());


      //  bind(PersistentManagerLog.class).to(PersistentManager.class);

    }
}
