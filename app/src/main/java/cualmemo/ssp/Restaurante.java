package cualmemo.ssp;

/**
 * Created by Memo on 21/11/2016.
 */

public class Restaurante {
    public double lat, lgt;
    public String  descripcion, precio, pais;

    public Restaurante() {
    }

    public Restaurante(double lat, double lgt, String descripcion, String precio, String pais) {
        this.lat = lat;
        this.lgt = lgt;
        this.descripcion = descripcion;
        this.precio = precio;
        this.pais = pais;
    }
}
