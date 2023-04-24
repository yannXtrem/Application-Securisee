/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classe;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Classe {

    private int id_classe;
    private String nom;
    
    public Classe(int id, String nom) {
        this.id_classe = id;
        this.nom = nom;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer cette classe ?");
        if(c == 0) {
            //delete
        }
        else {
            //annuler
        }
    }
    
    public void create() {
        
    }
    
    public static Classe fromResult(ResultSet result) throws SQLException {
        return new Classe(
                result.getInt("id"),
                result.getString("nom")
        );
    }
}
