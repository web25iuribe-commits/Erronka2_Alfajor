package app.model;
public class pieza {
    private String id;
    private String izena;
    private String deskribapena;
    private int prezioa;
    private String pisua;
    private int stock;
    private String id_pieza_Mota;

    public pieza(String id, String izena, String deskribapena,String pisua, int prezioa, int stock, String id_pieza_Mota) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.pisua = pisua; 
        this.prezioa = prezioa;
        this.stock = stock;
        this.id_pieza_Mota = id_pieza_Mota;
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
    public String getPisua() {
        return pisua;
    }  
    public void setPisua(String pisua) {
        this.pisua = pisua;
    }


    public int getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(int prezioa) {
        this.prezioa = prezioa;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getId_pieza_Mota() {
        return id_pieza_Mota;
    }
    public void setId_pieza_Mota(String id_pieza_Mota) {
        this.id_pieza_Mota = id_pieza_Mota;
    }
    @Override
    public String toString() {
        return "pieza [id=" + id + ", izena=" + izena + ", deskribapena=" + deskribapena + ", prezioa=" + prezioa
                + ", stock=" + stock + ", id pieza Mota=" + id_pieza_Mota + "]";
    }



}   