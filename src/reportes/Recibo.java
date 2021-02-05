/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Config;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author blade
 */
public class Recibo {

    static JasperReport reporte;

    public Recibo() {

    }

    public static void generarReporte() throws JRException {

        //Recibo.reporte = (JasperReport) JRLoader.loadObjectFromFile("C:/Users/blade/OneDrive/Documentos/NetBeansProjects/parcial_1/src/reportes/recibo.jasper");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_man", new String("100000000"));
        parameters.put("cliente", new String("pepito perez"));
        parameters.put("fecha", new String("02/03/2020"));
        parameters.put("telefono", new String("10777777777777"));
        parameters.put("direccion", new String("atalaya"));
        parameters.put("tecnico", new String("Don Ramon"));
        parameters.put("modelo", new String("TX-3000"));
        parameters.put("detalle", new String("is simply dummy text of the printing"
                + " and typesetting industry. Lorem Ipsum has been the industry's s"
                + "tandard dummy text ever since the 1500s, when an unknown printer "
                + "took a galley of type and scrambled it to make a type specimen book"));
        parameters.put("total", new String("550000"));
        parameters.put("observaciones", new String("s simply dummy text of the printings simply dummy text of the printing"));
        JasperPrint jasperPrint = null;
        jasperPrint = JasperFillManager.fillReport(
                Config.getRuta() + "\\src\\reportes\\recibo.jasper", (Map<String, Object>) parameters, new JREmptyDataSource());
//            JRPdfExporter exp = new JRPdfExporter();
//            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("recibo.pdf"));
//            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
//            exp.setConfiguration(conf);
//            exp.exportReport();

        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setVisible(true);

    }

    public static void generarReporte2() throws JRException {

        InputStream reportStream = null;
        try {
            //Recibo.reporte = (JasperReport) JRLoader.loadObjectFromFile("C:/Users/blade/OneDrive/Documentos/NetBeansProjects/parcial_1/src/reportes/recibo.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("id", new String("1000000"));
            reportStream = new FileInputStream(Config.getRuta() + "\\src\\reportes\\recibo.jrxml");
            //Iniciar reporte
            JasperReport report = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = new JasperPrint();
            JasperFillManager.fillReportToFile(report,
                    Config.getRuta() + "\\src\\reportes\\recibo.jasper", (Map<String, Object>) parameters, new JREmptyDataSource());
            try {
                //            JRPdfExporter exp = new JRPdfExporter();
//            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("recibo.pdf"));
//            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
//            exp.setConfiguration(conf);
//            exp.exportReport();
                reportStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reportStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
