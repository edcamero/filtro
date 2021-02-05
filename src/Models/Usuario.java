package Models;
// Generated 19 ene. 2020 23:54:08 by Hibernate Tools 4.3.1

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

    private int id;
    private String username;
    private String password;
    private int tipoUsuario;

    public Usuario(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(int id,String username, int tipoUsuario) {
        this.id = id;
        this.username = username;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
