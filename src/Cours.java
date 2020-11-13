import java.util.List;

public class Cours {

    private int coursID;
    private String name;
    private String sigle;
    private String description;
    List<Inscription> inscriptions;

    public Cours() {
    }

    public Cours(int coursID, String name, String sigle, String description){
        this.coursID = coursID;
        this.name = name;
        this.sigle = sigle;
        this.description = description;
    }

    public int getCoursID() {
        return coursID;
    }

    public void setCoursID(int coursID) {
        this.coursID = coursID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public void addInscription(Inscription ins){
        inscriptions.add(ins);
    }

    public void removeInscription(Inscription ins){
        inscriptions.remove(ins);
    }
}
