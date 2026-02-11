package app.model;
public class pieza {
    private String Id_pieza;
    private String Izena;
    private String Deskribapena;
    private String Pisua;
    private double  Prezioa;
    private int Stock;
    private String Id_pieza_Mota;

    public pieza(String id, String izena, String deskribapena,String pisua, double prezioa, int stock, String id_pieza_Mota) {
        this.Id_pieza = id;
        this.Izena = izena;
        this.Deskribapena = deskribapena;
        this.Pisua = pisua; 
        this.Prezioa = prezioa;
        this.Stock = stock;
        this.Id_pieza_Mota = id_pieza_Mota;
        }

    public String getId() {
        return Id_pieza;
    }

    public void setId(String id) {
        this.Id_pieza = id;
    }

    public String getIzena() {
        return Izena;
    }

    public void setIzena(String izena) {
        this.Izena = izena;
    }

    public String getDeskribapena() {
        return Deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.Deskribapena = deskribapena;
    }
    public String getPisua() {
        return Pisua;
    }  
    public void setPisua(String pisua) {
        this.Pisua = pisua;
    }


    public double getPrezioa() {
        return Prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.Prezioa = prezioa;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }
    public String getId_pieza_Mota() {
        return Id_pieza_Mota;
    }
    public void setId_pieza_Mota(String id_pieza_Mota) {
        this.Id_pieza_Mota = id_pieza_Mota;
    }
    @Override
    public String toString() {
        return "pieza [id=" + Id_pieza + ", izena=" + Izena + ", deskribapena=" + Deskribapena + ", prezioa=" + Prezioa
                + ", stock=" + Stock + ", id pieza Mota=" + Id_pieza_Mota + "]";
    }



}   