package app.model;
public class pieza {
    private String id;
    private String izena;
    private String deskribapena;
    private double prezioa;
    private String stock;

    public pieza(String id, String izena, String deskribapena, double prezioa, String stock) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "pieza [id=" + id + ", izena=" + izena + ", deskribapena=" + deskribapena + ", prezioa=" + prezioa
                + ", stock=" + stock + "]";
    }



}   