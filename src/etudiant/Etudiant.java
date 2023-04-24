/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etudiant;

import db.Donnees;
import java.sql.Date;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Etudiant {
    private String matricule;
    private String nom;
    private String prenom;
    private String quartier;
    private char sexe;
    private Date date_naiss;
    private int id_classe;

    public Etudiant(String matricule, String nom, String prenom, int id_classe, char sexe, Date date_naiss, String quartier) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.quartier = quartier;
        this.sexe = sexe;
        this.date_naiss = date_naiss;
        this.id_classe = id_classe;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", quartier=" + quartier + ", sexe=" + sexe + ", date_naiss=" + date_naiss + ", id_classe=" + id_classe + '}';
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }
    
    public void update() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir enregistrer ces modifications ?");
        if(c == 0) {
            //update
        }
        else {
            //annuler
        }
    }
    
    public void delete() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cet etudiant ?");
        if(c == 0) {
            //delete
        }
        else {
            //annuler
        }
    }
    
    public boolean create() {
        String query = "INSERT INTO gl3a.etudiant( matricule, nom, prenom, date_naiss, quartier, id_classe, sexe) VALUES ('"+matricule+"', '"+nom+"', '"+prenom+"', '"+date_naiss+"', '"+quartier+"', '"+id_classe+"', '"+sexe+"');";
        
        if(Donnees.execute(query)) {
            Donnees.etudiants.add(this);
            return true;
        }
            
        return false;
    }
    
    public static Etudiant fromResult(ResultSet result) throws SQLException {
        return new Etudiant(
                result.getString("matricule"),
                result.getString("nom"),
                result.getString("prenom"),
                result.getInt("id_classe"),
                result.getString("sexe").charAt(0),
                result.getDate("date_naiss"),
                result.getString("quartier")
        );
    }
}
