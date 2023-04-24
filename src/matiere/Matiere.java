/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matiere;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Matiere {
    private String nom;
    private short coefficient;
    private String code;

    public Matiere(String code, String nom, short coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public short getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(short coefficient) {
        this.coefficient = coefficient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cette matiere ?");
        if(c == 0) {
            //delete
        }
        else {
            //annuler
        }
    }
    
    public void create() {
        
    }
    
    public static Matiere fromResult(ResultSet result) throws SQLException {
        return new Matiere(
                result.getString("code"),
                result.getString("nom"),
                result.getShort("coef")
        );
    }
}
