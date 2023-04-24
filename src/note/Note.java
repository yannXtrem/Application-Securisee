/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package note;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Note {
    private String matricule_etudiant;
    private String code_matiere;
    private int note;

    public Note(String matricule_etudiant, String code_matiere, int note) {
        this.matricule_etudiant = matricule_etudiant;
        this.code_matiere = code_matiere;
        this.note = note;
    }

    public String getMatricule_etudiant() {
        return matricule_etudiant;
    }

    public void setMatricule_etudiant(String matricule_etudiant) {
        this.matricule_etudiant = matricule_etudiant;
    }

    public String getCode_matiere() {
        return code_matiere;
    }

    public void setCode_matiere(String code_matiere) {
        this.code_matiere = code_matiere;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
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
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cette note ?");
        if(c == 0) {
            //delete
        }
        else {
            //annuler
        }
    }
    
    public void create() {
        
    }
    
    public static Note fromResult(ResultSet result) throws SQLException {
        return new Note(
                result.getString("matricule_etudiant"),
                result.getString("code_matiere"),
                result.getInt("note")
        );
    }
    
    
}
