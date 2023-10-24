package dz.gouv.ppas.web.cagapps.business.reporting;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportService {

    <T> JasperPrint coompileReport(String name, List<T> data, Map<String, Object> params) throws IOException, JRException;

    ResponseEntity<String> exportToPDF(JasperPrint jasperPrint, String fileType, String fileName) throws JRException;

    byte[] exportToByte(JasperPrint jasperPrint) throws JRException;

    void exportToExcel(HttpServletResponse response, JasperPrint jasperPrint, String fileType, String fileName) throws IOException, JRException;
}
