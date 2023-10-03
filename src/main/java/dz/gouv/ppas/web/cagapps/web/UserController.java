package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDetailDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.ResponsableOrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.RoleEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MessageRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.ResetPasswordRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MessageResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationMembresServices;
import dz.gouv.ppas.web.cagapps.business.services.UserService;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.exceptions.ApiException;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.USER_ROOT)
public class UserController extends GenericRestController<UserService, User, UserDto, Long> {

    private final UserService userService;
    private final OrganisationMembresServices organisationMembresServices;

    @GetMapping(params = {"username"})
    public EntityResponse<UserDto> findByUsername(@RequestParam("username") String username) {
        Optional<UserDto> opt = userService.findByAccountName(username);
        if (opt.isPresent()) {
            return new EntityResponse(loadUserOrganisation(opt.get()));
        }
        return new EntityResponse("User Not Found.");
    }

    @PostMapping("/saveMembre")
    public EntityResponse<UserDto> saveUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @PatchMapping("/editMembre")
    public EntityResponse<UserDto> editUser(@RequestBody UserDto user) {
        return userService.editUser(user);
    }

    @GetMapping(params = {"id"})
    @Override
    public UserDto get(@RequestParam("id") Long idUser) {
        Optional<UserDto> userOpt = userService.findById(idUser);
        if (userOpt.isPresent()) {
            return loadUserOrganisation(userOpt.get());
        }
        throw new ApiException("User Not Found");
    }

    private UserDto loadUserOrganisation(UserDto userDto) {
        List<ResponsableOrganisationDto> responsableOrganisationList = organisationMembresServices.getOrganisationResponsable(userDto.getAccountName());
        if (!responsableOrganisationList.isEmpty()) {
            ResponsableOrganisationDto organisation = responsableOrganisationList.stream().filter(x -> x.isResponsable()).findAny().orElse(null);
            if (organisation != null) {
                userDto.setSecretaire(organisation.isResponsable());
                userDto.setOrganisationResponsableId(organisation.getOrganisationId());
                userDto.setOrganisationResponsable(organisation.getOrganisationLabel());
                userDto.setOrganisationResponsableAdresse(organisation.getOrganisationAdresse());
            }
        }
        List<OrganisationDetailDto> organisationList = responsableOrganisationList.stream().map(x -> new OrganisationDetailDto(x.getOrganisationId(), x.getOrganisationId(), x.getOrganisationLabel())).collect(Collectors.toList());
        userDto.setAdmin(userDto.getRolesList().stream().filter(x -> x.getLabel().equals(RoleEnum.Administrateur.toString())).findAny().isPresent());
        userDto.setMembre(userDto.getRolesList().stream().filter(x -> x.getLabel().equals(RoleEnum.Membre.toString())).findAny().isPresent());
        userDto.setOrganisationList(organisationList);
        return userDto;
    }

    @PostMapping("/resetPassword")
    public MessageResponse resetPassword(@RequestBody ResetPasswordRequest passwordRequest) {
        return userService.resetPassword(passwordRequest);
    }

}
