/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enseignant;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Enseignant {
    private String matricule;
    private String nom;
    private String quartier;
    private String code_matiere;

    public Enseignant(String matricule, String nom, String quartier, String code_matiere) {
        this.matricule = matricule;
        this.nom = nom;
        this.quartier = quartier;
        this.code_matiere = code_matiere;
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

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getCode_matiere() {
        return code_matiere;
    }

    public void setCode_matiere(String code_matiere) {
        this.code_matiere = code_matiere;
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
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cet enseignant ?");
        if(c == 0) {
            //delete
        }
        else {
            //annuler
        }
    }
    
    public void create() {
        
    }
    
    public static Enseignant fromResult(ResultSet result) throws SQLException {
        return new Enseignant(
                result.getString("matricule"),
                result.getString("nom"),
                result.getString("quartier"),
                result.getString("code_matiere")
        );
    }
}
