import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

public class Etudiant implements Serializable {

    private int etudiantid;
    private String fname;
    private String lname;
    private int age;
    private List<Inscription> inscriptions;


    public Etudiant(){

    }
    public Etudiant(int etudianid, String fname, String lname, int age){
        this.etudiantid =  etudianid;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public int getEtudiantid() {
        return etudiantid;
    }

    public void setEtudiantid(int etudiantid) {
        this.etudiantid = etudiantid;
    }

    public void addInscription(Inscription ins){
        inscriptions.add(ins);
    }

    public void removeInscription(Inscription ins){
        inscriptions.remove(ins);
    }


}
