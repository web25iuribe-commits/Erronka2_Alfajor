package app.model;

import java.time.LocalDate;

public class erabiltzailea {
    private String id_erabiltzailea;
    private String izena;   
    private String abizena1;
    private String NAN;
    private String Helbidea;
    private int postaKodea;
    private String email;
    private LocalDate jaiotze_data;
    private LocalDate alta_data;

    public erabiltzailea(String id_erabiltzailea, String izena, String abizena1, String NAN, String Helbidea, int postaKodea, String email, LocalDate jaiotze_data, LocalDate alta_data) {
        this.id_erabiltzailea = id_erabiltzailea;
        this.izena = izena;
        this.abizena1 = abizena1;
        this.NAN = NAN;
        this.Helbidea = Helbidea;
        this.postaKodea = postaKodea;
        this.email = email;
        this.jaiotze_data = jaiotze_data;
        this.alta_data = alta_data;
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

    public LocalDate getJaiotze_data() {
        return jaiotze_data;
    }

    public void setJaiotze_data(LocalDate jaiotze_data) {
        this.jaiotze_data = jaiotze_data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAlta_data() {
        return alta_data;
    }

    public void setAlta_data(LocalDate alta_data) {
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
