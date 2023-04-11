package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private Connection cnx;
    String url = "jdbc:mysql://localhost:3306/pidev";
    String user = "root";
    String pwd = "";
    public static Connexion ct;

    private Connexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("conexion etabli");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connexion getInstance() {
        if (ct == null) {
            ct = new Connexion();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

}
