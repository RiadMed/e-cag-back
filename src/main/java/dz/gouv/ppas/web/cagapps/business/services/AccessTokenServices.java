package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.AccessTokenDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.AccessToken;
import dz.restmapping.apps.services.GenericService;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface AccessTokenServices extends GenericService<AccessToken, AccessTokenDto, UUID> {

    void deleteTokenByUser(Long userId);

    boolean verifyExpiration(AccessTokenDto token);

    Optional<AccessTokenDto> findByUser(Long userId);
}
