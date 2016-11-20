package cualmemo.ssp;

/**
 * Created by Memo on 07/11/2016.
 */

public class Usuario {
    public int id;
    public String usuario,correo,contrasena,pais;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String correo, String contrasena, String pais) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.pais = pais;
    }
}