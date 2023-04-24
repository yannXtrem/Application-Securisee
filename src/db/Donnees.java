/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

import classe.Classe;
import enseignant.Enseignant;
import etudiant.Etudiant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.login;
import matiere.Matiere;
import note.Note;
import utilisateur.Profile;
import utilisateur.Utilisateur;
import yannscrypt.YannsCrypt;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class Donnees {
    public final static int AFFICHER_ETUDIANTS = 0;
    public final static int CREER_ETUDIANTS = 1;
    public final static int MODIFIER_ETUDIANTS = 2;
    public final static int SUPPRIMER_ETUDIANTS = 3;
    
    public final static int AFFICHER_ENSEIGNANTS = 4;
    public final static int CREER_ENSEIGNANTS = 5;
    public final static int MODIFIER_ENSEIGNANTS = 6;
    public final static int SUPPRIMER_ENSEIGNANTS = 7;
    
    public final static int AFFICHER_MATIERES = 8;
    public final static int CREER_MATIERES = 9;
    public final static int MODIFIER_MATIERES = 10;
    public final static int SUPPRIMER_MATIERES = 11;
    
    public final static int AFFICHER_NOTES = 12;
    public final static int CREER_NOTES = 13;
    public final static int MODIFIER_NOTES = 14;
    public final static int SUPPRIMER_NOTES = 15;
    
    public final static int AFFICHER_UTILISATEURS = 16;
    public final static int CREER_UTILISATEURS = 17;
    public final static int MODIFIER_UTILISATEURS = 18;
    public final static int SUPPRIMER_UTILISATEURS = 19;
    
    public final static int AFFICHER_PROFILES = 20;
    public final static int CREER_PROFILES = 21;
    public final static int MODIFIER_PROFILES = 22;
    public final static int SUPPRIMER_PROFILES = 23;
    
    private static final String connectionUrl="jdbc:postgresql://localhost:5432/gl3a";
    private static Connection con;
    public static ArrayList<Enseignant> enseignants;
    public static ArrayList<Etudiant> etudiants;
    public static ArrayList<Matiere> matieres;
    public static ArrayList<Note> notes;
    public static ArrayList<Utilisateur> utilisateurs;
    public static ArrayList<Profile> profiles;
    public static ArrayList<Classe> classes;
    public static Utilisateur utilisateurConnecte;
    public static Profile profileUtilisateurConnecte;
    public static void controlAction(JFrame source, int action, JFrame target){
        if(Donnees.profileUtilisateurConnecte.getPrivileges()[action] == '1') {
            target.setVisible(true);
            source.dispose();
        }
        else {
            actionInterdite(source);
        }
    }
    public static void actionInterdite(JFrame source){
        Object act = JOptionPane.showInputDialog(null, "Vous n'avez pas le droit d'effectuer cette action.\nChoissiez une action rapide ci-dessous :", "Tentative de violation de l'integrite", JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Se connecter a un autre compte ?", "Ne rien faire"}, "Se connecter a un autre compte");
            if(act != null){
                if(act.toString().equals("Se connecter a un autre compte ?")){
                    new login().setVisible(true);
                    source.dispose();
                }
                
            }
    }
    public Donnees(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "postgres", "The0last0mc");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Donnees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Donnees.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        enseignants = new ArrayList<>();initEnseignants();
        etudiants = new ArrayList<>();initEtudiants();
        matieres = new ArrayList<>();initMatieres();
        notes = new ArrayList<>();initNotes();
        utilisateurs = new ArrayList<>();initUtilisateurs();
        profiles = new ArrayList<>();initProfiles();
        classes = new ArrayList<>();initClasses();
    }
    private static Profile getProfile(Utilisateur u){
        for(int i = 0; i < profiles.size(); i++){
            if(profiles.get(i).getNom().equals(u.getProfile()))
            {
                return profiles.get(i);
            }
        }
        return null;
    }
    public static boolean authentification(String nom, String mdp){
        for(int i = 0; i < utilisateurs.size(); i++){
            System.out.println("ne:"+nom+" me:"+mdp+" - nd:"+utilisateurs.get(i).getNom()+" md:"+utilisateurs.get(i).getMdp());
            if(YannsCrypt.crypterUnTexte(mdp, nom).equals(utilisateurs.get(i).getMdp()) && nom.equals(utilisateurs.get(i).getNom()))
            {
                utilisateurConnecte = utilisateurs.get(i);
                profileUtilisateurConnecte = getProfile(utilisateurs.get(i));
                return true;
            }
        }
        return false;
    }
    public static boolean execute(String stmt) {
        try(Statement pstmt = con.createStatement();) {
            if(pstmt.execute(stmt)){
                JOptionPane.showMessageDialog(null, "Echec d'execution de la requete : " + stmt, "Operation echouee", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "L'execution de la requete s'est terminee sans aucune erreur", "Operation reussie", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Donnees.class.getName()).log(Level.SEVERE, "Erreur lors de l'execution de la requete", ex);
            JOptionPane.showMessageDialog(null, "Echec d'execution de la requete : " + stmt + "\n"+ex.getMessage(), "Operation echouee : "+ex.getErrorCode(),JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private static void initEnseignants() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.enseignant;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                enseignants.add(Enseignant.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initEtudiants() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.etudiant;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                etudiants.add(Etudiant.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initMatieres() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.matiere");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                matieres.add(Matiere.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initNotes() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.note;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                notes.add(Note.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initClasses() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.classe;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                classes.add(Classe.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initUtilisateurs() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.utilisateur;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                utilisateurs.add(Utilisateur.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void initProfiles() {
        try (CallableStatement cstmt = con.prepareCall("SELECT * FROM gl3a.profile;");) {
            // Execute a stored procedure that returns some data.
            ResultSet rs = cstmt.executeQuery();
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                profiles.add(Profile.fromResult(rs));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Note> notesEn(String code_matiere) {
        ArrayList<Note> ns = new ArrayList<>();
        for(int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            if(n.getCode_matiere().equals(code_matiere)) {
                ns.add(n);
            }
        }
        return ns;
    }
}
