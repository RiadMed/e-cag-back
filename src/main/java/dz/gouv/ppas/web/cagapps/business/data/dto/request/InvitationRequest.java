package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvitationRequest implements Serializable {

    private String mailSubject;
    private String mailDescription;
    private String[] mails;
    private String[] cc;

    public InvitationRequest(String[] mails) {
        this.mails = mails;
    }
}
