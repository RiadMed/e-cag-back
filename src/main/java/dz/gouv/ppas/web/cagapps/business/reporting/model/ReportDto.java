package dz.gouv.ppas.web.cagapps.business.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto implements Serializable {
    private String title;
    private String description;
    private Date date;
    private String user;
    private String wilaya;
    private Integer groupBy;
    private String sort;
    private String format;
    private String qrCode;
}
