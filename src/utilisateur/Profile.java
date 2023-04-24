/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilisateur;

import db.Donnees;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Profile {
    private String nom;
    private String privileges;

    public Profile(String nom, String privileges) {
        this.nom = nom;
        this.privileges = privileges;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char[] getPrivileges() {
        return privileges.toCharArray();
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
    
    public boolean update() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir enregistrer ces modifications ?");
        if(c == 0) {
            if(Donnees.execute("UPDATE gl3a.profile SET privileges='"+privileges+"' WHERE nom='"+nom+"';")){
                for(int i = 0; i < Donnees.profiles.size(); i++){
                    if(Donnees.profiles.get(i).getNom().equals(nom))
                    {
                        Donnees.profiles.remove(i);
                        Donnees.profiles.add(i,this);
                        return true;
                    }
                }
            }
            else return false;
        }
        return false;
    }
    
    public boolean delete() {
        int c = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir supprimer ce profile ?");
        if(c == 0) {
            if(Donnees.execute("DELETE gl3a.profile WHERE nom='"+nom+"';")){
                for(int i = 0; i < Donnees.profiles.size(); i++){
                    if(Donnees.profiles.get(i).getNom().equals(nom))
                    {
                        Donnees.profiles.remove(i);
                        return true;
                    }
                }
            }
            else return false;
        }
        return false;
    }
    
    public boolean create() {
        if(Donnees.execute("INSERT INTO gl3a.profile(nom, privileges) VALUES ('"+nom+"', '"+privileges+"');"))
        {
            Donnees.profiles.add(this);
            return true;
        }
        else return false;
    }
    
    public static Profile fromResult(ResultSet result) throws SQLException {
        String p = result.getString("privileges");
        String c = "";
        if(p != null) c = p;
        return new Profile(
                result.getString("nom"),
                c
        );
    }
}
