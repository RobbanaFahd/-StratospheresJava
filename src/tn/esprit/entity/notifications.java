package tn.esprit.entity;

/**
 *
 * @author Fahd
 */
public class notifications {
    
    int id;
    String title;
    String message;
    String recipient;
    String sender;
    String typesang;

    public notifications() {
    }

    public notifications(String title, String message, String recipient, String sender, String typesang) {
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
        this.typesang = typesang;
    }

    public notifications(int id, String title, String message, String recipient, String sender, String typesang) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
        this.typesang = typesang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTypesang() {
        return typesang;
    }

    public void setTypesang(String typesang) {
        this.typesang = typesang;
    }

    @Override
    public String toString() {
        return "notifications{" + "id=" + id + ", title=" + title + ", message=" + message + ", recipient=" + recipient + ", sender=" + sender + ", typesang=" + typesang + '}';
    }
    
    
    
}
