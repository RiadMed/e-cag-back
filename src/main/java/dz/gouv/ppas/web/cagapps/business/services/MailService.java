package dz.gouv.ppas.web.cagapps.business.services;


import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.InvitationRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Organisation;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    MailResponse<String> sendMail(MailRequest mailRequest) throws UnsupportedEncodingException, MessagingException;

    MailResponse<String> sendMailDeCreation(MailRequest mailRequest, String usercode, String password);

    MailResponse<String> sendMailDePlannification(MailRequest mailRequest, String secretaire, OrganisationDto organisation, String date);

    MailResponse<String> sendMailInvitations(MailRequest mailRequest, OrganisationDto organisation, String date, String adresse);

    MailResponse<String> sendMotDePasseTemporaire(MailRequest mailRequest, String username, String password);
}
