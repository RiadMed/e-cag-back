package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RolesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.RoleEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.*;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.business.repositories.UserDao;
import dz.gouv.ppas.web.cagapps.business.services.MailService;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationMembresServices;
import dz.gouv.ppas.web.cagapps.business.services.UserService;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
@Transactional
public class UserServiceIMPL extends GenericServiceImpl<UserDao, User, UserDto, Long> implements UserService {

    private final UserDao userDao;
    private final MailService mailService;
    private final OrganisationMembresServices organisationMembresServices;

    @Override
    public Optional<UserDto> findByAccountName(String accountName) {
        Optional<User> userOpt = userDao.findByAccountName(accountName);
        if (userOpt.isPresent()) {
            return Optional.of(mapToDto(userOpt.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> findUsersByEmail(String email) {
        Optional<User> userOpt = userDao.findUsersByEmail(email);
        if (userOpt.isPresent()) {
            return Optional.of(mapToDto(userOpt.get()));
        }
        return Optional.empty();
    }

    private EntityResponse<UserDto> validateUserBeforeSave(UserDto user) {
        if (user.getOrganisationIds().isEmpty()) {
            return new EntityResponse<>("Imposible de sauvegarder un utilsateur sans organisations. (3001)");
        }
        if (user.getRolesList().isEmpty()) {
            return new EntityResponse<>("Imposible de sauvegarder un utilsateur sans roles. (3002)");
        }
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getPhone() == null) {
            return new EntityResponse<>("Vérifier tous les champs obilgatoires. (3003)");
        }
        Optional<UserDto> isUserExist = findUsersByEmail(user.getEmail());
        if (isUserExist.isPresent()) {
            return new EntityResponse<>("Cette utilisateur a déja un compte E-CAG. (3004)");
        }
        return new EntityResponse<>(user);
    }

    private EntityResponse<UserDto> validateUserBeforeEdit(UserDto user) {
        if (user.getId() == null) {
            return new EntityResponse<>("Imposible de sauvegarder un utilsateur sans roles. (3002)");
        }
        if (user.getOrganisationList().isEmpty()) {
            return new EntityResponse<>("Imposible de sauvegarder un utilsateur sans organisations. (3001)");
        }
        if (user.getRolesList().isEmpty()) {
            return new EntityResponse<>("Imposible de sauvegarder un utilsateur sans roles. (3002)");
        }
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getPhone() == null) {
            return new EntityResponse<>("Vérifier tous les champs obilgatoires. (3003)");
        }
        return new EntityResponse<>(user);
    }

    @Override
    public EntityResponse<UserDto> saveUser(UserDto user) {
        EntityResponse<UserDto> response = validateUserBeforeSave(user);
        if (response.isSuccess()) {
            String username = (user.getFirstName() + "." + user.getLastName()).toLowerCase(Locale.US);
            int i = 0;
            if (findByAccountName(username).isPresent()) {
                i++;
            }
            if (i > 0)
                user.setAccountName(username + "0" + i);
            else
                user.setAccountName(username);
            String password = AppsUtils.generateRandomPassword(10, 48, 122);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passEncode = passwordEncoder.encode(password);
            user.setPassword(passEncode);
            user.setEnabled(true);
            user.setAccountExpires(false);
            user.setCredentials(true);
            user.getOrganisationIds().stream().forEach(x -> {
                organisationMembresServices.save(new OrganisationMembresDto(x.getKey(), user.getAccountName(), LocalDateTime.now(), x.getKey() == user.getOrganisationResponsableId(), true));
            });
            user.setNouveauCompte(true);
            UserDto saved = save(user);
            if (saved != null) {
                List<String> mails = Arrays.asList(saved.getEmail());
                mailService.sendMailDeCreation(new MailRequest(mails.toArray(new String[0])), saved.getAccountName(), password);
                return new EntityResponse(saved, "Utilisateur enregistré avec succés");
            }
            return new EntityResponse<>("Erreur d'enregistrement de l'utilisateur. (3005)");
        } else
            return response;
    }

    @Override
    public EntityResponse<UserDto> editUser(UserDto userJson) {
        EntityResponse<UserDto> response = validateUserBeforeEdit(userJson);
        if (response.isSuccess()) {
            Optional<UserDto> userEdit = findById(userJson.getId());
            if (userEdit.isPresent()) {
                UserDto userDB = userEdit.get();
                userJson.getOrganisationList().stream().forEach(x -> {
                    if (organisationMembresServices.findByIdOrgUsername(userDB.getAccountName(), x.getKey()) == null)
                        organisationMembresServices.save(new OrganisationMembresDto(x.getKey(), userDB.getAccountName(), LocalDateTime.now(), x.getKey() == userJson.getOrganisationResponsableId(), true));
                });
                userDB.setPhone(userJson.getPhone());
                userDB.setRolesList(userJson.getRolesList());
                UserDto saved = save(userDB);
                if (saved != null) {
                    return new EntityResponse(saved, "Utilisateur modifié avec succés");
                }
                return new EntityResponse<>("Erreur d'enregistrement de l'utilisateur. (3005)");
            }
        }
        return response;
    }

    @Override
    public MessageResponse resetPassword(ResetPasswordRequest passwordRequest) {
        if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmPassword())) {
            return new MessageResponse(false, "les 2 mot de passes ne sont pas identique. (3006)");
        }
        Optional<UserDto> userOptional = findById(passwordRequest.getUserId());
        if (!userOptional.isPresent()) {
            return new MessageResponse(false, "Erreur d'enregisterement des mots de passe. (3007)");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPass = passwordEncoder.encode(passwordRequest.getNewPassword());

        UserDto user = userOptional.get();
        user.setPassword(newPass);
        user.setNouveauCompte(false);
        UserDto saved = save(user);
        if (saved == null) {
            return new MessageResponse(false, "Erreur d'enregisterement des mots de passe. (3007)");
        }
        return new MessageResponse(true, "Mot de passe changer avec succès.");
    }

    @Override
    public MessageResponse sendPassword(MessageRequest messageRequest) {
        Optional<UserDto> userOpt = findUsersByEmail(messageRequest.getMail());
        if (!userOpt.isPresent())
            return new MessageResponse(false, "Aucun utilisateur n'est enregister à cette adresse. (3008)");
        String password = AppsUtils.generateRandomPassword(10, 48, 122);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passEncode = passwordEncoder.encode(password);
        UserDto user = userOpt.get();
        user.setPassword(passEncode);
        user.setNouveauCompte(true);
        UserDto saved = save(user);
        if (saved != null) {
            List<String> mails = Arrays.asList(messageRequest.getMail());
            mailService.sendMotDePasseTemporaire(new MailRequest(mails.toArray(new String[0])), userOpt.get().getAccountName(), password);
            return new MessageResponse(true, "Mot de passe temporaire envoyé, Veuillez vérifier votre boir courriel.");
        }
        return new MessageResponse(false, "Erreur dans l'envoir du mot de passe temporaire. (3009)");
    }


    private boolean isResponsable(List<RolesDto> list) {
        return list.stream().filter(role -> role.getLabel().equals(RoleEnum.Secretaire.toString())).findAny().isPresent();
    }


}
