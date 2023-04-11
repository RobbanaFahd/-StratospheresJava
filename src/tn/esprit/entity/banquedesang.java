package tn.esprit.entity;


/**
 *
 * @author Fahd
 */
public class banquedesang {
    
    int id;
    String nom;
    String adresse;
    int tel;
    float longitude;
    float latitude;

    public banquedesang() {
    }

    public banquedesang(int id, String nom, String adresse, int tel, float longitude, float latitude) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    

    public banquedesang(String nom, String adresse, int tel, float longitude, float latitude) {
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
        @Override
    public String toString() {
        return "banquedesang{" + "nom=" + nom + ", adresse=" + adresse + ", tel=" + tel + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }

    
}
