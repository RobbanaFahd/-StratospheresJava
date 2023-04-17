/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.banquedesang;
import tn.esprit.tools.Connexion;

/**
 *
 * @author Fahd
 */
public class ServiceBanqueDeSang {

    Connection cnx;

    public ServiceBanqueDeSang() {
        cnx = Connexion.getInstance().getCnx();

    }

    public void ajouter(banquedesang b) {
        try {
            String sql = "INSERT INTO `banque_de_sang`(`nom`,`adresse`,`tel`,`longitude`,`latitude`) VALUES (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, b.getNom());
            ste.setString(2, b.getAdresse());
            ste.setInt(3, b.getTel());
            ste.setFloat(4, b.getLatitude());
            ste.setFloat(5, b.getLongitude());
            ste.executeUpdate();
            System.out.println("Banque de Sang Ajoutée");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public void supprimer(int id, banquedesang b) {
        try {
            String sql = "DELETE FROM `banque_de_sang` WHERE  id = ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, b.getId());
            ste.executeUpdate();
            System.out.println("Banque de sang supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(String nom, String adresse, banquedesang b) {
        String sql = "update banque_de_sang set nom=? , adresse=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setString(2, adresse);
            ste.setInt(3, b.getId());
            ste.executeUpdate();
            System.out.println("Banque de Sang Modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<banquedesang> afficher() {
        List<banquedesang> ct = new ArrayList<>();
        try {
            String sql = "select * from banque_de_sang";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                banquedesang c = new banquedesang(s.getInt("id"), s.getString("nom"),
                        s.getString("adresse"), s.getInt("tel"), s.getFloat("longitude"), s.getFloat("latitude"));
                ct.add(c);

            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

    public void modifierBanque(banquedesang banque) {
        String sql = "update banque_de_sang set nom=? , adresse=? , tel=? , longitude=?, latitude=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, banque.getNom());
            ste.setString(2, banque.getAdresse());
            ste.setInt(3, banque.getTel());
            ste.setFloat(4, banque.getLongitude());
            ste.setFloat(5, banque.getLatitude());
            ste.setInt(6, banque.getId());
            ste.executeUpdate();
            System.out.println("Banque de Sang Modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }



}
