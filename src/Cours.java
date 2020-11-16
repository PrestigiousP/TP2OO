import java.util.List;

import javax.persistence.*;

//Déclaration en tant que bean grâce à l'annotation Entity
@Entity
@Table(name="cours") //Précise la table, car elle n'a pas le même nom que la classe
public class Cours {

    //Associer un champ de la table à la propriété en tant que clé primaire et demander une génération automatique
    @Id
    @GeneratedValue
    private int coursID;

    private String name;
    private String sigle;
    private String description;

    @Transient //Je ne tiens pas compte du champ car il ne fait pas partie de la BD
    private List<InscriptionBean> inscriptions;

    //Constructeur sans argument
    public Cours() { }

    //Accesseurs mutateurs
    public int getCoursID() { return coursID; }
    public void setCoursID(int coursID) { this.coursID = coursID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSigle() { return sigle; }
    public void setSigle(String sigle) { this.sigle = sigle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<InscriptionBean> getInscriptions() { return inscriptions; }
    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }

    //Ajouter et supprimer des inscriptions
    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }

    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

    public String sqlQuery(String s){
        String query = null;
        if (s == "insert") {
            query = "insert into cours" + " (coursid, name, sigle, description)"
                    + " values (?, ?, ?, ?)";
        }
        if (s == "select"){
            query = "select * from cours";
        }
        return query;
    }
}
