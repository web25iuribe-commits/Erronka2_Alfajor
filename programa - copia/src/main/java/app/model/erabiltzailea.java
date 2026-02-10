package app.model;

import java.sql.Date;

public class erabiltzailea {
    private String id_erabiltzailea;
    private String izena;   
    private String abizena1;
    private Date jaiotze_data;
    private String email;
    private Date alta_data;
    private String Helbidea;
    private String NAN;
    private int postaKodea;

    public erabiltzailea(String id_erabiltzailea, String izena, String abizena1, Date jaiotze_data, String email, Date alta_data, String helbidea, String NAN, int postaKodea) {
        this.id_erabiltzailea = id_erabiltzailea;
        this.izena = izena;
        this.abizena1 = abizena1;
        this.jaiotze_data = jaiotze_data;
        this.email = email;
        this.alta_data = alta_data;
        this.Helbidea = helbidea;
        this.NAN = NAN;
        this.postaKodea = postaKodea;
    }

    public String getId_erabiltzailea() {
        return id_erabiltzailea;
    }

    public void setId_erabiltzailea(String id_erabiltzailea) {
        this.id_erabiltzailea = id_erabiltzailea;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena1() {
        return abizena1;
    }

    public void setAbizena1(String abizena1) {
        this.abizena1 = abizena1;
    }

    public Date getJaiotze_data() {
        return jaiotze_data;
    }

    public void setJaiotze_data(Date jaiotze_data) {
        this.jaiotze_data = jaiotze_data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAlta_data() {
        return alta_data;
    }

    public void setAlta_data(Date alta_data) {
        this.alta_data = alta_data;
    }

    public String getHelbidea() {
        return Helbidea;
    }

    public void setHelbidea(String helbidea) {
        Helbidea = helbidea;
    }

    public String getNAN() {
        return NAN;
    }

    public void setNAN(String nAN) {
        NAN = nAN;
    }
    public int getPostaKodea() {
        return postaKodea;
    }

    public void setPostaKodea(int postaKodea) {
        this.postaKodea = postaKodea;
    }
      @Override
    public String toString() {
        return "erabiltzailea [id_erabiltzailea=" + id_erabiltzailea + ", izena=" + izena + 
        ", abizena=" + abizena1 + ", jaiotze_data=" + jaiotze_data + ", email=" + email + 
        ", alta_data=" + alta_data + ", helbidea=" + Helbidea + ", NAN=" + NAN + "]";
    }

}
