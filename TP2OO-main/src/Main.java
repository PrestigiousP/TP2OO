import beans.CoursBean;
import beans.EtudiantBean;
import beans.InscriptionBean;
import pm.PersistentManager;
import pm.retrieve.RAnnotationsProcessor;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main<T> {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException { //Je crée un

        PersistentManager db = new PersistentManager(); //Persisten


        List<CoursBean> listcours = db.retrieveSet(CoursBean.class, "SELECT * FROM cours"); //Retourne une liste de cours

        CoursBean cours = new CoursBean();
        cours.setCoursID(5);
        cours.setDescription("Salut");
        cours.setName("pute");
        cours.setSigle("INFMENCALICE");
        List<InscriptionBean> listecours = new ArrayList<>();
        InscriptionBean inscription1 = new InscriptionBean();
        inscription1.setCoursid(7);
        inscription1.setEtudiantid(7);
        inscription1.setInscriptionid(5);
        listecours.add(inscription1);
        cours.setInscriptions(listecours);
        db.insert(cours);
       //Annotation dbIgnore sur les champs qu'on veut pas chercher
        System.out.println(listcours.toString());
    }
}