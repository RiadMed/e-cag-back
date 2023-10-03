package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.AccessTokenDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.AccessToken;
import dz.gouv.ppas.web.cagapps.business.repositories.AccessTokenDao;
import dz.gouv.ppas.web.cagapps.business.services.AccessTokenServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class AccessTokenServicesIMPL extends GenericServiceImpl<AccessTokenDao, AccessToken, AccessTokenDto, UUID> implements AccessTokenServices {

    private final AccessTokenDao accessTokenDao;

    @Override
    public void deleteTokenByUser(Long userId) {
        accessTokenDao.deleteTokenByUser(userId);
    }

    @Override
    public boolean verifyExpiration(AccessTokenDto token) {
        return token.getExpirationDate().compareTo(Instant.now()) > 0;
    }

    @Override
    public Optional<AccessTokenDto> findByUser(Long userId) {
        return accessTokenDao.findByUser(userId).map(data -> mapToDto(data));
    }
}
