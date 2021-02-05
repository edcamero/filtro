/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Models.Cliente;
import Models.EquipoCliente;
import Models.Mantenimiento;
import Models.MantenimientoEquipo;
import Models.Repuesto;
import Models.Tecnico;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import reportes.Recibo;

/**
 *
 * @author blade
 */
public class prueba {

    public static void main(String arg[]) {
        try {
            Config.loadConfig();
            Fachada fachada = Fachada.getInstancia();
            Cliente cliente = fachada.getCliente(1);
            //cliente.setPurificadores();
            Tecnico tecnico = fachada.getTecnico(1);
            Mantenimiento mantenimiento = new Mantenimiento(cliente);
            mantenimiento.setFechaMan(new Date());
            mantenimiento.setTecnico(tecnico);
            EquipoCliente equipoCliente = cliente.getEquiposCliente().get(0);
            
            MantenimientoEquipo mantEquipo = new MantenimientoEquipo(mantenimiento, equipoCliente);//listo
            
            mantEquipo.setBujia(fachada.getBujia(1));//agregar bujia al mantenimiento
            
            Repuesto repuesto = fachada.getRepuesto(1);
            //RepuestoMante respMant = new RepuestoMante(mantEquipo, repuesto, 5);
            mantEquipo.agregarRepuestos(repuesto, 5);
            
            mantenimiento.getMantenimientoEquipo().add(mantEquipo);
            fachada.saveMantenimiento(mantenimiento);
            Recibo.generarReporte();
        } catch (JRException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
