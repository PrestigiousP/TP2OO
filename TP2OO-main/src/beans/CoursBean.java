package beans;

import pm.annotations.DbEntity;
import pm.annotations.DbIgnored;
import pm.annotations.DbJoin;
import pm.annotations.PK;

import java.io.Serializable;
import java.util.List;

@DbEntity(table="cours")
public class CoursBean implements Serializable {
    //Bean Type Simple
    @PK(pk="coursid")
    private int coursid;
    private String name;
    private String sigle;
    private String description;

    //Bean type collection de bean
    @DbJoin(innerkeys = "coursid", outerkeys = "coursid")
    private List<InscriptionBean> inscriptions;         //Annotation qui fait matcher dans
    //Quand on va arriver pour retrieve la liste d'inscription : SELECT * FROM INSCRIPTION WHERE ID = COURSID

    //Constructeur vide
    public CoursBean() { }

    //Getter and setter
    public int getCoursID() { return coursid; }

    public void setCoursID(int coursid) { this.coursid = coursid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSigle() { return sigle; }

    public void setSigle(String sigle) { this.sigle = sigle; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<InscriptionBean> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<InscriptionBean> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
