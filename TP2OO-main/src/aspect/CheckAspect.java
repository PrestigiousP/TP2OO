package aspect;

import beans.CoursBean;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class CheckAspect implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try{
            Object[] objects = methodInvocation.getArguments();
            String className = this.getClass().getName();
            //faudra mettre ça générique
            Method method = this.getClass().getMethod("retrieveSet");
            Logger log = Logger.getLogger(className);
            Object result = methodInvocation.proceed();
        }
        catch (Exception e){
            //fiare dequoi pour intercepter le result
        }
        




        System.out.println("testeststt");
        return null;
    }
}
