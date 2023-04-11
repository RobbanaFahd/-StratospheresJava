/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidv;

import tn.esprit.entity.banquedesang;
import tn.esprit.entity.notifications;
import tn.esprit.service.ServiceBanqueDeSang;
import tn.esprit.service.Servicenotifications;

/**
 *
 * @author Fahd
 */
public class Pidv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServiceBanqueDeSang b1 = new ServiceBanqueDeSang();
        Servicenotifications n1 = new Servicenotifications();

        banquedesang b2 = new banquedesang("Banque de sang BouHajla", "Kairouan Bouhajla", 71254254, 35.397131F, 10.048456F);
        banquedesang b3 = new banquedesang("Banque de sang Ben arous", "ben arous", 71254254, 35.397131F, 10.048456F);
        banquedesang b4 = new banquedesang(30, "Banque de sang Ben arous", "ben arous", 71254254, 35.397131F, 10.048456F);
        banquedesang b5 = new banquedesang("Banque de sang Ben arous", "ben arous", 71254254, 35.397131F, 10.048456F);
        banquedesang b6 = new banquedesang(31, "Banque de sang Ben arous", "ben arous", 71254254, 35.397131F, 10.048456F);
        banquedesang b7 = new banquedesang(32, "Banque de sang Ben arous", "ben arous", 71254254, 35.397131F, 10.048456F);
        banquedesang b8 = new banquedesang(33, "Banque de sang Tunis", "Tunis", 71254254, 35.397131F, 10.048456F);
        banquedesang b9 = new banquedesang(34, "Banque de sang zdz", "Tunis", 71254254, 35.397131F, 10.048456F);
        banquedesang b10 = new banquedesang("Banque de sang abcded", "Tunis", 71254254, 35.397131F, 10.048456F);
        notifications n2 = new notifications("Hello", "world", "robbana.fahd99@gmail.com", "robbana.fahd99@gmail.com", "Type A");

        //b1.ajouter(b10);
        // b1.supprimer(b2);
        //n1.ajouter(n2);
        //b1.supprimer(29,b7);
        // n1.supprimer(n2);
        //b1.ajouter(b9);
        //b1.modifier("banque de sang Jerba", "Midoun", b8);
        //b1.getAllServiceBanqueDeSang();
        // n1.getAllServicenotifications();
    }

}
