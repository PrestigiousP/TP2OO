package aspect;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import java.sql.SQLException;

public class CheckAspect implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try{
            return methodInvocation.proceed();
        }
        catch(InstantiationException e){
            System.out.println("Exception: La classe ne peut pas être instancié.");
            System.out.println("Stack trace: " + e.fillInStackTrace());
        }
        catch(IllegalAccessException e){
            System.out.println("Exception: Accès illégal.");
            System.out.println("Stack trace: " + e.fillInStackTrace());
        }
        catch(SQLException e){
            System.out.println("Exception: Exception SQL.");
            System.out.println("Stack trace: " + e.fillInStackTrace());
        }
        catch (Exception e){
            System.out.println("Exception: Non déterminé");
            System.out.println("Stack trace: " + e.fillInStackTrace());
        }
        return null;
    }
}
