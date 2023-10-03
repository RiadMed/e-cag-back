package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDto implements Serializable {

    private UUID idAccess;
    private Instant expirationDate;
    private Long userId;
}
