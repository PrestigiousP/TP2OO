package beans;

import pm.annotations.DbEntity;
import pm.annotations.DbJoin;
import pm.annotations.PK;

import java.io.Serializable;
import java.util.List;

@DbEntity(table="etudiant")
public class EtudiantBean implements Serializable {

    //Type simple
    @PK(pk = "etudiantid")
    private int etudiantid;
    private String fname;
    private String lname;
    private int age;

    //Type collectionInnerBean
    @DbJoin(innerkeys = "etudiantid", outerkeys = "etudiantid")
    private List<InscriptionBean> inscriptions;

    //Constructeur vide
    public EtudiantBean(){}

    //Getter and setter
    public String getFname() { return fname; }

    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }

    public void setLname(String lname) { this.lname = lname; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public List<InscriptionBean> getInscriptions() { return inscriptions; }

    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }

    public int getEtudiantID() { return etudiantid; }

    public void setEtudiantID(int etudiantid) { this.etudiantid = etudiantid; }

}
