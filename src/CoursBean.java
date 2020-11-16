import java.io.Serializable;
import java.util.List;

public class CoursBean implements Serializable {
    private String tableName = "cours";
    private int coursID;
    private String name;
    private String sigle;
    private String description;
    private List<InscriptionBean> inscriptions;
    private static int count = 0;

    //public CoursBean() { count++; this.setCoursID(count);}
    public CoursBean() { }

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

    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }

    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

    public int getCount() { return count;}

    public String getTableName(){return tableName;}

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
