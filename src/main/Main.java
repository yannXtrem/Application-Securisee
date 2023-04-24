/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import db.Donnees;

/**
 *
 * @author Yannick MVOGO BEKONO
 */

public class Main {
    public static Donnees d;
    public static void main(String args[]){
        d = new Donnees();
        if(!checkSession()){
            login l = new login();
            l.setVisible(true);
        }
        else {
            new Accueil().setVisible(true);
        }
    }
    public static boolean checkSession(){
        return false;
    }
}