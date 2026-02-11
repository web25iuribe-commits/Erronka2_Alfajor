package app.model;

public class makina {
    
    private String Id_makina;
    private String Izena;
    private String Instalazio_data;
    private String Deskribapena;
    private int Potentzia;

    public makina(String Id_makina, String Izena, String Instalazio_data, String Deskribapena, int Potentzia) {
        this.Id_makina = Id_makina;
        this.Izena = Izena;
        this.Instalazio_data = Instalazio_data;
        this.Deskribapena = Deskribapena;
        this.Potentzia = Potentzia;
    }

    public String getId_makina() {
        return Id_makina;
    }

    public void setId_makina(String id_makina) {
        Id_makina = id_makina;
    }

    public String getIzena() {
        return Izena;
    }

    public void setIzena(String izena) {
        Izena = izena;
    }

    public String getInstalazio_data() {
        return Instalazio_data;
    }

    public void setInstalazio_data(String instalazio_data) {
        Instalazio_data = instalazio_data;
    }

    public String getDeskribapena() {
        return Deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        Deskribapena = deskribapena;
    }

    public int getPotentzia() {
        return Potentzia;
    }

    public void setPotentzia(int potentzia) {
        Potentzia = potentzia;
    }
    @Override
    public String toString() {
        return "makina{" +
                "Id_makina='" + Id_makina + '\'' +
                ", Izena='" + Izena + '\'' +
                ", Instalazio_data='" + Instalazio_data + '\'' +
                ", Deskribapena='" + Deskribapena + '\'' +
                ", Potentzia=" + Potentzia +
                '}';
    }
}
