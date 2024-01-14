import java.io.Serializable;

public class Etudiant implements Serializable {
    private String nom;
    private String prenom;
    private String groupe;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String groupe) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupe = groupe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }
}
