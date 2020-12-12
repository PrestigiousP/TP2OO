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
    //@FK(fk="description")
    private String description;

    //Bean type collection de bean
    //@DbJoin(innerkeys = "coursid", outerkeys = "coursid")
    @DbIgnored
    private List<InscriptionBean> inscriptions;         //Annotation qui fait matcher dans
    //Quand on va arriver pour retrieve la liste d'inscription : SELECT * FROM INSCRIPTION WHERE ID = COURSID

    public CoursBean() { }

    public int getCoursID() { return coursid; }

    public void setCoursID(int coursid) { this.coursid = coursid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSigle() { return sigle; }

    public void setSigle(String sigle) { this.sigle = sigle; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

//    public List<InscriptionBean> getInscriptions() { return inscriptions; }
//
//    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }
//
//    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }
//
//    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

    //public String getTableName(){return tableName;}

//    public String sqlQuery(String s){
//        String query = null;
//        if (s == "insert") {
//            query = "insert into cours" + " (coursid, name, sigle, description)"
//                    + " values (?, ?, ?, ?)";
//        }
//        if (s == "select"){
//            query = "select * from cours";
//        }
//        return query;
//    }
}
