package beans;

import pm.annotations.DbEntity;
import pm.annotations.DbIgnored;
import pm.annotations.DbJoin;

import java.io.Serializable;
import java.util.List;

@DbEntity(table="etudiant")
public class EtudiantBean implements Serializable {

    private int etudiantID;
    private String fname;
    private String lname;
    private int age;

    //@DbJoin(innerkeys = "etudiantID", outerkeys = "etudiantID")
    @DbIgnored
    private List<InscriptionBean> inscriptions;
//    private static int count = 0;

    //public beans.EtudiantBean(){ count++; this.setEtudiantid(count);}

    public EtudiantBean(){}

    public String getFname() { return fname; }

    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }

    public void setLname(String lname) { this.lname = lname; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

//    public List<InscriptionBean> getInscriptions() { return inscriptions; }
//
//    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }

    public int getEtudiantID() { return etudiantID; }

    public void setEtudiantID(int etudiantid) { this.etudiantID = etudiantID; }

//    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }
//
//    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

//    public String getTableName(){ return tableName;}
//
//    public int getCount() { return count;}
//
//    public String sqlQuery(String s){
//        String query = null;
//        if (s == "insert") {
//            query = "insert into etudiant" + " (etudiantid, fname, lname, age)"
//                    + " values (?, ?, ?, ?)";
//        }
//        if (s == "select"){
//            query = "select * from etudiant";
//        }
//        return query;
//    }
}
