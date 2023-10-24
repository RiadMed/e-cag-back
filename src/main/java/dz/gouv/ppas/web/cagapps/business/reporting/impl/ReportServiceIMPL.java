package dz.gouv.ppas.web.cagapps.business.reporting.impl;

import dz.gouv.ppas.web.cagapps.business.reporting.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
@Service
public class ReportServiceIMPL implements ReportService {
    @Override
    public <T> JasperPrint coompileReport(String name, List<T> data, Map<String, Object> params) throws IOException, JRException {
        InputStream file = new ClassPathResource("reports/" + name + ".jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(file);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        return jasperPrint;
    }

    @Override
    public ResponseEntity<String> exportToPDF(JasperPrint jasperPrint, String fileType, String fileName) throws JRException {
        byte[] bytes = exportToByte(jasperPrint);
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename(fileName + ".pdf").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity
                .ok()
                .header("Content-Type", fileType + "; charset=UTF-8")
                .headers(headers)
                .body(Base64.getEncoder().encodeToString(bytes));
    }

    @Override
    public byte[] exportToByte(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public void exportToExcel(HttpServletResponse response, JasperPrint jasperPrint, String fileType, String fileName) throws IOException, JRException {
        response.setContentType(fileType);
        response.setHeader("Content-Disposition", "inline; " + fileName + ".xls");
        final OutputStream outputStream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setCollapseRowSpan(true);
        xlsReportConfiguration.setIgnoreGraphics(false);
        xlsReportConfiguration.setWhitePageBackground(false);
        exporter.setConfiguration(xlsReportConfiguration);
        exporter.exportReport();
    }
}
