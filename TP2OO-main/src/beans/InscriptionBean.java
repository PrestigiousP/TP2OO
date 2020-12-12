package beans;

import pm.annotations.*;

import java.io.Serializable;

@DbEntity(table="inscription")
public class InscriptionBean implements Serializable {

    //Type simple
    private int inscriptionid;
    @FK(fk="etudiantid")
    private int etudiantid;
    @FK(fk="coursid")
    private int coursid;

    //Inner bean mais ils sont ignored pour éviter une boucle infini
    @DbIgnored
    private CoursBean cours;
    @DbIgnored
    private EtudiantBean etudiant;

    //Constructeur vide
    public InscriptionBean(){ }

    //Getter and setter
    public int getInscriptionid() { return inscriptionid; }

    public void setInscriptionid(int inscriptionid) { this.inscriptionid = inscriptionid; }

    public int getEtudiantid() { return etudiantid; }

    public void setEtudiantid(int etudiantid) { this.etudiantid = etudiantid; }

    public int getCoursid() { return coursid; }

    public void setCoursid(int coursid) { this.coursid = coursid; }

    public CoursBean getCours() { return cours; }

    public void setCours(CoursBean cours) { this.cours = cours; }

    public EtudiantBean getEtudiant() { return etudiant; }

    public void setEtudiant(EtudiantBean etudiant) { this.etudiant = etudiant; }
}
