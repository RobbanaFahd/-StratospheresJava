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
import tn.esprit.entity.notifications;
import tn.esprit.tools.Connexion;

/**
 *
 * @author Fahd
 */
public class Servicenotifications {
        Connection cnx;

    public Servicenotifications() {
        cnx = Connexion.getInstance().getCnx();

    }
    public void ajouter(notifications n) {
        try {

            String sql = "INSERT INTO `notifications`(`title`,`message`,`recipient`,`sender`,`typesang`) VALUES (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, n.getTitle());
            ste.setString(2, n.getMessage());
            ste.setString(3, n.getRecipient());
            ste.setString(4, n.getSender());
            ste.setString(5, n.getTypesang());
            ste.executeUpdate();
            System.out.println("Notifications Ajoutée");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }
     public void supprimer(notifications n) {
        try {
            String sql = "DELETE FROM `notifications` WHERE  id = ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, n.getId());
            ste.executeUpdate();
            System.out.println("notifications supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void modifier(String title, String message, String recipient, String sender, String typesang, notifications n) {
        String sql = "update notifications set title=? , message=? , recipient=? , sender=? , typesang=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, title);
            ste.setString(2, message);
            ste.setString(3, recipient);
            ste.setString(4, sender);
            ste.setString(5, typesang);
            ste.setInt(3, n.getId());
            ste.executeUpdate();
            System.out.println("notifications Modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public List<notifications> afficher() {
        List<notifications> ct = new ArrayList<>();
        try {
            String sql = "select * from notifications";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                notifications c = new notifications(s.getInt("id"), s.getString("title"),
                        s.getString("message"), s.getString("recipient"), s.getString("sender"), s.getString("typesang"));
                ct.add(c);

            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

}
