import java.io.Serializable;

public class InscriptionBean implements Serializable {

    private int inscriptionid;
    private int etudiantid;
    private int coursid;
    private CoursBean cours;
    private EtudiantBean etudiant;

    public InscriptionBean(){ }

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
