/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilisateur;
import db.Donnees;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import yannscrypt.YannsCrypt;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Utilisateur {
    private String nom;
    private String mdp;
    private String profile;

    public Utilisateur(String nom, String mdp, String profile) {
        this.nom = nom;
        this.mdp = mdp;
        this.profile = profile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public boolean update() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir enregistrer ces modifications ?");
        String cryptMdp = YannsCrypt.crypterUnTexte(mdp, nom);
        if(c == 0) {
            if(Donnees.execute("UPDATE gl3a.utilisateur SET mdp='"+cryptMdp+"', prof='"+profile+"' WHERE nom = '"+nom+"';")){
                for(int i = 0; i < Donnees.utilisateurs.size(); i++){
                    if(Donnees.utilisateurs.get(i).getNom().equals(nom))
                    {
                        Donnees.utilisateurs.remove(i);
                        Donnees.utilisateurs.add(i, this);
                        return true;
                    }
                }
            }
            else return false;
        }
        return false;
    }
    
    public boolean delete() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cet utilisateur ?");
        if(c == 0) {
            if(Donnees.execute("DELETE FROM gl3a.utilisateur WHERE nom = '"+nom+"';")){
                for(int i = 0; i < Donnees.utilisateurs.size(); i++){
                    if(Donnees.utilisateurs.get(i).getNom().equals(nom))
                    {
                        Donnees.utilisateurs.remove(i);
                        return true;
                    }
                }
            }
            else return false;
        }
        return false;
    }
    
    public boolean create() {
        String cryptMdp = YannsCrypt.crypterUnTexte(mdp, nom);
        if(Donnees.execute("INSERT INTO gl3a.utilisateur(nom, mdp, prof) VALUES ('"+nom+"', '"+cryptMdp+"', '"+profile+"');")) {
            Donnees.utilisateurs.add(this);
            return true;
        }
        
        return false;
    }
    
    public static Utilisateur fromResult(ResultSet result) throws SQLException {
        return new Utilisateur(
                result.getString("nom"),
                result.getString("mdp"),
                result.getString("prof")
        );
    }
}
