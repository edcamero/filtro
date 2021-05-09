package Models;
// Generated 19 ene. 2020 23:54:08 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.Objects;

/**
 * MantenimientoHasPurificador generated by hbm2java
 */
public class MantenimientoEquipo implements java.io.Serializable {

    private int id;
    private int idBujia;
    private Bujia bujia;
    private int idMante;
    private int idEquiCliente;
    private Mantenimiento mantenimiento;
    private EquipoCliente equipoCliente;
    private Integer costosRepuestos;
    private Integer ingresosRespuestos;
    private ArrayList<MantenimientoRepuesto> repuestos = new ArrayList<MantenimientoRepuesto>(0);

    public MantenimientoEquipo(Mantenimiento mantenimiento, EquipoCliente equipoCliente) {
        this.mantenimiento = mantenimiento;
        this.idMante = mantenimiento.getId();
        this.idEquiCliente=equipoCliente.getId();
        this.equipoCliente = equipoCliente;
        this.costosRepuestos = 0;
        this.ingresosRespuestos = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int idManEqui) {
        this.id = idManEqui;
    }

    public int getIdMante() {
        return mantenimiento.getId();
    }

    public void setIdMante(int idMante) {
        this.idMante = idMante;
    }

    public Bujia getBujia() {
        return this.bujia;
    }

    public Mantenimiento getMantenimiento() {
        return this.mantenimiento;
    }

    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Integer getCostosRepuestos() {
        return this.costosRepuestos;
    }

    public void setCostosRepuestos(Integer costosRepuestos) {
        this.costosRepuestos = costosRepuestos;
    }

    public Integer getIngresosRespuestos() {
        return this.ingresosRespuestos;
    }

    public void setIngresosRespuestos(Integer ingresosRespuestos) {
        this.ingresosRespuestos = ingresosRespuestos;
    }

    public ArrayList<MantenimientoRepuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(ArrayList<MantenimientoRepuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public int getIdBujia() {
        return idBujia;
    }

    public int getIdEquiCliente() {
        return idEquiCliente;
    }

    public EquipoCliente getEquipoCliente() {
        return equipoCliente;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MantenimientoEquipo other = (MantenimientoEquipo) obj;

        if (this.idEquiCliente != other.idEquiCliente) {
            return false;
        }
        if (!Objects.equals(this.equipoCliente, other.equipoCliente)) {
            return false;
        }
        return true;
    }

    public void agregarRepuestos(Repuesto repuesto, int cantidad) {
        MantenimientoRepuesto respMant = new MantenimientoRepuesto(this, repuesto, cantidad);
        this.repuestos.add(respMant);
        this.costosRepuestos = this.costosRepuestos + repuesto.getValorCosto() * cantidad;
       // this.mantenimiento.setCostoServicioTecnico(repuesto.getValorCosto() * cantidad + this.mantenimiento.getCostoServicioTecnico());
        this.ingresosRespuestos = this.ingresosRespuestos + repuesto.getValorVenta() * cantidad;
        this.mantenimiento.setValorMantenimiento(this.mantenimiento.getValorMantenimiento() + repuesto.getValorVenta() * cantidad);
    }

    public void setBujia(Bujia bujia) {
        this.bujia = bujia;
        this.idBujia = bujia.getId();
        this.costosRepuestos = this.costosRepuestos + bujia.getValorCosto();
        //this.mantenimiento.setCostoServicioTecnico(bujia.getValorCosto() + this.mantenimiento.getCostoServicioTecnico());
        this.ingresosRespuestos = this.ingresosRespuestos + bujia.getValorVenta();
        this.mantenimiento.setValorMantenimiento(this.mantenimiento.getValorMantenimiento() + bujia.getValorVenta());
       
    }

    public void eliminarBujia() {
        if (bujia != null) {
            this.costosRepuestos = this.costosRepuestos - bujia.getValorCosto();
            this.mantenimiento.setCostoServicioTecnico(this.mantenimiento.getCostoServicioTecnico() - bujia.getValorCosto());
            this.ingresosRespuestos = this.ingresosRespuestos - bujia.getValorVenta();
            this.mantenimiento.setValorMantenimiento(this.mantenimiento.getValorMantenimiento() - bujia.getValorVenta());
        }

    }
    public void eliminarRepuesto(int index) {
        MantenimientoRepuesto respuestoMant=this.repuestos.remove(index);
        if (respuestoMant!= null) {
            this.costosRepuestos = this.costosRepuestos - respuestoMant.getCostosRepuestos();
            this.mantenimiento.setCostoServicioTecnico(this.mantenimiento.getCostoServicioTecnico() - bujia.getValorCosto());
            this.ingresosRespuestos = this.ingresosRespuestos - respuestoMant.getIngresosRespuestos();
            this.mantenimiento.setValorMantenimiento(this.mantenimiento.getValorMantenimiento() - bujia.getValorVenta());
        }

    }

    public String misRepuestos() {
        String respuesta = "";
        for (MantenimientoRepuesto repuesto : repuestos) {
            respuesta = respuesta + repuesto.getCantidadRep() + " " + repuesto.getRepuesto().getNombre() + "-";
        }
        return respuesta;
    }
}
