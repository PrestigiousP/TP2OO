package beans;

import java.io.Serializable;
import java.util.List;

public class EtudiantBean implements Serializable {

    private String tableName = "etudiant";
    private int etudiantid;
    private String fname;
    private String lname;
    private int age;
    private List<InscriptionBean> inscriptions;
    private static int count = 0;

    //public beans.EtudiantBean(){ count++; this.setEtudiantid(count);}
    public EtudiantBean(){}

    public String getFname() { return fname; }

    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }

    public void setLname(String lname) { this.lname = lname; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public List<InscriptionBean> getInscriptions() { return inscriptions; }

    public void setInscriptions(List<InscriptionBean> inscriptions) { this.inscriptions = inscriptions; }

    public int getEtudiantid() { return etudiantid; }

    public void setEtudiantid(int etudiantid) { this.etudiantid = etudiantid; }

    public void addInscription(InscriptionBean ins){ inscriptions.add(ins); }

    public void removeInscription(InscriptionBean ins){ inscriptions.remove(ins); }

    public String getTableName(){ return tableName;}

    public int getCount() { return count;}

    public String sqlQuery(String s){
        String query = null;
        if (s == "insert") {
            query = "insert into etudiant" + " (etudiantid, fname, lname, age)"
                    + " values (?, ?, ?, ?)";
        }
        if (s == "select"){
            query = "select * from etudiant";
        }
        return query;
    }
}
