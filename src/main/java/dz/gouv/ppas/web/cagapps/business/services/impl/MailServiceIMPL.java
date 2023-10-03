package dz.gouv.ppas.web.cagapps.business.services.impl;

import com.sun.mail.util.MailConnectException;
import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MailGabaritDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.GabaritEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.InvitationRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailResponse;
import dz.gouv.ppas.web.cagapps.business.services.MailGabaritService;
import dz.gouv.ppas.web.cagapps.business.services.MailService;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationServices;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class MailServiceIMPL implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailGabaritService mailGabaritService;
    private final OrganisationServices organisationServices;


    @Override
    public MailResponse<String> sendMail(MailRequest mailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = this.getMessageHelpers(message, mailRequest);
            helper.setText(mailRequest.getMailDescription(), true);
            javaMailSender.send(message);
            return new MailResponse<>(true, "Email envoyé avec succes.");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    @Override
    public MailResponse<String> sendMailDeCreation(MailRequest mailRequest, String usercode, String password) {
        MailGabaritDto gabarit = getGabait(GabaritEnum.C001.toString());
        String mailTemplate = getMailTemplate(gabarit.getGabarit());
        String gab1 = mailTemplate.replace("${usercode}", usercode);
        String gab2 = gab1.replace("${password}", password);
        mailRequest.setMailDescription(gab2);
        mailRequest.setMailSubject(gabarit.getSujet());
        return sendMail(mailRequest);
    }

    @Override
    public MailResponse<String> sendMailDePlannification(MailRequest mailRequest, String secretaire, OrganisationDto organisation, String date) {
        MailGabaritDto gabarit = getGabait(GabaritEnum.C002.toString());
        String gabaritText = MessageFormat.format(gabarit.getGabarit(), secretaire, organisation.getOrganisationTypeLabel(), organisation.getCode(), date);
        String mailTemplate = getMailTemplate(gabaritText);
        mailRequest.setMailDescription(mailTemplate);
        mailRequest.setMailSubject(gabarit.getSujet());
        List<String> mails = Arrays.asList(organisation.getAdminMail());
        mailRequest.setMails(mails.toArray(new String[0]));
        List<String> cc = Arrays.asList(organisation.getResponsableMail());
        mailRequest.setCc(cc.toArray(new String[0]));
        return sendMail(mailRequest);
    }

    @Override
    public MailResponse<String> sendMailInvitations(MailRequest mailRequest, OrganisationDto organisation, String date, String adresse) {
        MailGabaritDto gabarit = getGabait(GabaritEnum.C003.toString());
        String gabaritText = MessageFormat.format(gabarit.getGabarit(), date, organisation.getOrganisationTypeLabel(), organisation.getCode(), adresse);
        String mailTemplate = getMailTemplate(gabaritText);
        mailRequest.setMailDescription(mailTemplate);
        mailRequest.setMailSubject(gabarit.getSujet());
        List<String> emails = Arrays.asList(organisation.getResponsableMail(), organisation.getSecretaireMail());
        mailRequest.setCc(emails.toArray(new String[0]));
        return sendMail(mailRequest);
    }

    @Override
    public MailResponse<String> sendMotDePasseTemporaire(MailRequest mailRequest, String username, String password) {
        MailGabaritDto gabarit = getGabait(GabaritEnum.C004.toString());
        String gabaritText = MessageFormat.format(gabarit.getGabarit(), replaceUserName(username), password);
        String mailTemplate = getMailTemplate(gabaritText);
        mailRequest.setMailDescription(mailTemplate);
        mailRequest.setMailSubject(gabarit.getSujet());
        return sendMail(mailRequest);
    }

    private MimeMessageHelper getMessageHelpers(MimeMessage message, MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@e-cag.dz", mailRequest.getMailSubject());
        helper.setSubject(mailRequest.getMailSubject());
        helper.setTo(mailRequest.getMails());
        if (mailRequest.getCc() != null) {
            helper.setCc(mailRequest.getCc());
        }
//        ClassPathResource resource = new ClassPathResource("static/images/logo.png");
//        helper.addInline("logoImage", resource);
        return helper;
    }

    private String getMailTemplate(String gabarit) {
        String mailTemplate = mailGabaritService.findMailGabaritByCode(GabaritEnum.C000.toString()).getGabarit();
        return mailTemplate.replace("${mailBody}", gabarit);
    }

    private MailGabaritDto getGabait(String code) {
        return mailGabaritService.findMailGabaritByCode(code);
    }

    private String getReplacedText(String gabarit, String... parametres) {
        return MessageFormat.format(gabarit, parametres);
    }

    private String replaceUserName(String username) {
        return StringUtils.overlay(username, StringUtils.repeat("*", username.length() - 1), 1, username.length() - 1);
    }

}
