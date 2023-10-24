package dz.gouv.ppas.web.cagapps.business.data.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationStatistic implements Serializable {

    private int lu;
    private int nonLu;
}
