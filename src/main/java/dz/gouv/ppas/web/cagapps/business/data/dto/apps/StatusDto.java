package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StatusDto implements Serializable {
    @JMap
    private Integer id;
    @JMap
    private String label;
    @JMap
    private String description;
    @JMap
    private String color;
    @JMap
    private Boolean disabled;
}
