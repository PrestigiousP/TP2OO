import java.io.Serializable;
import java.util.List;

public class CoursBean implements Serializable {
    public String test = "test";
    private String tableName = "cours";
    private int coursID;
    private String name;
    private String sigle;
    private String description;
    private List<InscriptionBean> inscriptions;
    private String url;
    private String query;
    private static int count = 0;

    public CoursBean() { count++; this.setCoursID(count);}

    public int getCoursID() { return coursID; }

    private void setCoursID(int coursID) { this.coursID = coursID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSigle() { return sigle; }

    public void setSigle(String sigle) { this.sigle = sigle; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<InscriptionBean> getInscriptions() { return inscriptions; }

    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }

    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }

    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

    public void setUrl(String url) { this.url = url;}

    public int getCount() { return count;}

    public String getTableName(){return tableName;}

}
