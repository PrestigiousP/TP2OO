import beans.CoursBean;
import pm.PersistentManager;

import java.util.List;

public class Main<T> {
    public static void main(String[] args) { //Je crée un
        PersistentManager db = new PersistentManager(); //Persisten
        List<CoursBean> listcours = db.retrieveSet(CoursBean.class, "SELECT * FROM cours"); //Retourne une liste de cours
        //Annotation dbIgnore sur les champs qu'on veut pas chercher
    }
}