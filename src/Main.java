import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main<T> {
    public static void main(String[] args) {
        ArrayList<CoursBean> listCours;
//        ArrayList<CoursBean> listCours = new ArrayList<CoursBean>();
        ArrayList<EtudiantBean> listEtudiants = new ArrayList<EtudiantBean>();
        ArrayList<InscriptionBean> listInscriptions = new ArrayList<InscriptionBean>();
        try{
           // listCours = retrieve();
//            ArrayList<CoursBean> listeCours = new ArrayList<CoursBean>();
//            ArrayList<EtudiantBean> listeEtudiants = new ArrayList<EtudiantBean>();
//            ArrayList<InscriptionBean> listeInscriptions = new ArrayList<InscriptionBean>();
            boolean bool = true;
            while(bool){
                System.out.println("-----------------Menu-----------------");
                System.out.println("Entrez un chiffre: ");
                System.out.println("1- Insérer");
                System.out.println("2- Retirer");
                System.out.println("3- Mettre à jour");
                System.out.println("4- Supprimer");
                System.out.println("5- Quitter");
                Scanner obj = new Scanner(System.in);
                int choix = Integer.parseInt(obj.nextLine());
                switch (choix){
                    case 1:
//                        inserer(listeCours, listeEtudiants, listeInscriptions);
                        menuInserer();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Entrez un nombre entre 1 et 5");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
//    public static void inserer(ArrayList<CoursBean> list, ArrayList<EtudiantBean> list2, ArrayList<InscriptionBean> list3){
    public static void menuInserer(){
        boolean bool = true;
        while(bool){
            System.out.println("-----------------Insérer-----------------");
            System.out.println("Entrez un chiffre: ");
            System.out.println("1- Insérer dans la table cours");
            System.out.println("2- Insérer dans la table etudiant");
            System.out.println("3- Insérer dans la table inscription");
            Scanner obj = new Scanner(System.in);
            int choix = Integer.parseInt(obj.nextLine());
            switch (choix){
                case 1:
                    createNewCourse();
                    break;
                case 2:
                    createNewEtudiant();
                case 3:
                    //createNewInscription();
                default :
                    System.out.println("Entrez un nombre entre 1 et 3");
            }
        }
    }

    public static void createNewCourse(){
        try{
            CoursBean cours = new CoursBean();
            String s;
            System.out.print("Entrez le nom du cours: ");
            Scanner obj = new Scanner(System.in);
            s = obj.nextLine();
            cours.setName(s);
            System.out.print("Entrez le sigle du cours: ");
            s = obj.nextLine();
            cours.setSigle(s);
            System.out.print("Entrez la description du cours: ");
            s = obj.nextLine();
            cours.setDescription(s);

            //Établit une connexion et selectionne une table
            DatabaseManager databaseManager = new DatabaseManager(cours);
            databaseManager.insert();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void createNewEtudiant(){
        try{
            EtudiantBean etudiant = new EtudiantBean();
            String s;
            System.out.print("Entrez le prénom de l'étudiant: ");
            Scanner obj = new Scanner(System.in);
            s = obj.nextLine();
            etudiant.setFname(s);
            System.out.print("Entrez le nom de famille de l'étudiant: ");
            s = obj.nextLine();
            etudiant.setLname(s);
            System.out.print("Entrez son âge: ");
            int age = Integer.parseInt(obj.nextLine());
            etudiant.setAge(age);

            //Établit une connexion et selectionne une table
            DatabaseManager databaseManager = new DatabaseManager(etudiant);
            databaseManager.insert();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

//    public List<T> retrieve(Class<T> c, String sql){
//
//    }
//    public static void createNewInscription(){
//        try{
//            InscriptionBean inscription = new InscriptionBean();
//            String s;
//            System.out.print("Entrez le prén: ");
//            Scanner obj = new Scanner(System.in);
//            s = obj.nextLine();
//            inscription.set(s);
//            System.out.print("Entrez le nom de famille de l'étudiant: ");
//            s = obj.nextLine();
//            inscription.setLname(s);
//            System.out.print("Entrez la description du cours: ");
//            int age = Integer.parseInt(obj.nextLine());
//            inscription.setAge(age);
//
//            //Établit une connexion et selectionne une table
//            SQLManager sqlManager = new SQLManager(etudiant);
//            sqlManager.insert();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//    }
}