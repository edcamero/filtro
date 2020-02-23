package Models;
// Generated 19 ene. 2020 23:54:08 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Bujia generated by hbm2java
 */
public class Bujia  implements java.io.Serializable {


     private int id;
     private String nombre;
     private int vidaUtil;
     private int valorCosto;
     private int valorVenta;
     private Set<MantenimientoEquipo> mantenimientoHasPurificadors = new HashSet<MantenimientoEquipo>(0);

    public Bujia(String nombre, int vidaUtil, int valorCosto, int valorVenta) {
        this.nombre = nombre;
        this.vidaUtil = vidaUtil;
        this.valorCosto = valorCosto;
        this.valorVenta = valorVenta;
    }

    public Bujia(int id, String nombre, int vidaUtil, int valorCosto, int valorVenta) {
        this.id = id;
        this.nombre = nombre;
        this.vidaUtil = vidaUtil;
        this.valorCosto = valorCosto;
        this.valorVenta = valorVenta;
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

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public int getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(int valorCosto) {
        this.valorCosto = valorCosto;
    }

    public int getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(int valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Set<MantenimientoEquipo> getMantenimientoHasPurificadors() {
        return mantenimientoHasPurificadors;
    }

    public void setMantenimientoHasPurificadors(Set<MantenimientoEquipo> mantenimientoHasPurificadors) {
        this.mantenimientoHasPurificadors = mantenimientoHasPurificadors;
    }

   



}


