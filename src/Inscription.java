public class Inscription {

    private int inscriptionid;
    private int etudiantid;
    private int coursid;
    private Cours cours;
    private Etudiant etudiant;

    public Inscription(){

    }
    public Inscription(int inscriptionid, int etudiantid, int coursid, Cours cours, Etudiant etudiant){
        this.inscriptionid = inscriptionid;
        this.etudiantid = etudiantid;
        this.coursid = coursid;
        this.cours = cours;
        this.etudiant = etudiant;
    }

    public int getInscriptionid() {
        return inscriptionid;
    }

    public void setInscriptionid(int inscriptionid) {
        this.inscriptionid = inscriptionid;
    }

    public int getEtudiantid() {
        return etudiantid;
    }

    public void setEtudiantid(int etudiantid) {
        this.etudiantid = etudiantid;
    }

    public int getCoursid() {
        return coursid;
    }

    public void setCoursid(int coursid) {
        this.coursid = coursid;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
