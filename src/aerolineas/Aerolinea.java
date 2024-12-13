
package aerolineas;


public class Aerolinea {
    private int id;
    private String nombre;
    private String pais;
    private String cod_iata;
    private String cod_icao;

    public Aerolinea(String nombre, String pais, String cod_iata, String cod_icao) {
        this.nombre = nombre;
        this.pais = pais;
        this.cod_iata = cod_iata;
        this.cod_icao = cod_icao;
    }
    
    public Aerolinea(int id, String nombre, String pais, String cod_iata, String cod_icao) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.cod_iata = cod_iata;
        this.cod_icao = cod_icao;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCod_iata() {
        return cod_iata;
    }

    public void setCod_iata(String cod_iata) {
        this.cod_iata = cod_iata;
    }

    public String getCod_icao() {
        return cod_icao;
    }

    public void setCod_icao(String cod_icao) {
        this.cod_icao = cod_icao;
    }
    
    //metodo para imprimir datos a string
    @Override
    public String toString(){
        return nombre + " " + pais + " " + cod_iata + " " + cod_icao;
    }
}
