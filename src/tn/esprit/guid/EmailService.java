/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private String mail = "ahmedaziz.rebhi@esprit.tn";
    private String password = "223JMT0181";

    public void envoyer(String recepient, String newPassword) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ahmedaziz.rebhi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject("Notifications sent");
            message.setText("Dear Donor,\n\n a notifications has been sent. Please check your account to see notifications \n\n\nIf you have any comments or questions don’t hesitate to reach us at ahmedaziz.rebhi@esprit.tn\nPlease feel free to respond to this email. It was sent from a monitored email address, and we would love to hear from you.\nThank you,\nGFL Admin");

            // Send the message
            Transport.send(message);

            System.out.println("Notifications sent " + recepient);
        } catch (MessagingException e) {
            System.out.println("Error sending Notifications email: " + e.getMessage());
        }
    }
}
