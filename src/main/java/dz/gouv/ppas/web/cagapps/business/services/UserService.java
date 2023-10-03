package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MessageRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.ResetPasswordRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MessageResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.restmapping.apps.services.GenericService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface UserService extends GenericService<User, UserDto, Long> {

    Optional<UserDto> findByAccountName(String accountName);

    Optional<UserDto> findUsersByEmail(String email);

    EntityResponse<UserDto> saveUser(UserDto user);

    EntityResponse<UserDto> editUser(UserDto user);

    MessageResponse resetPassword(ResetPasswordRequest passwordRequest);

    MessageResponse sendPassword(MessageRequest messageRequest);
}
