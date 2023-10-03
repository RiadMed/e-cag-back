package dz.gouv.ppas.web.cagapps.tools;

public class MailUtils {

    public static String getMailContent(String code, String title, String body, String date) {
        String mailContent = "<div " +
                "style='width:98%;height: 70px;background: #f1f1f1;border: 1px solid #e9e9e9;margin: 5px;font-size: 22px;font-weight: 500;font-family: Century Gothic'>" +
                "<img src='cid:logoImage' width='284' style='margin: 0px 15px 0px 5px;' />" +
                "</div>";
        mailContent += "<br/><div style='width:96%;padding:10px;border-bottom: 1px solid #d0d0d0;margin: 5px;height: auto;margin-top: 10px'>" +
                " <h4 style='color: #343a40;margin: 10px;font-family: Century Gothic'>" + code + " - " + title + "</h4>";
        mailContent += "<h6 style='color: #343a40;margin: 10px;font-weight: 400;font-family: Century Gothic'>Bonjour,</h6>";
        mailContent += "<h6 style='color: #343a40;margin: 10px 10px 10px 15px;font-weight: 400;font-family: Century Gothic'> " + body + ".</h6>";
        mailContent += "<h6 style='color: #343a40;margin: 10px;font-weight: 400;font-family: Century Gothic'>Cordialement.</h6><br/>";
        mailContent += "<span style='color: #343a40;margin: 10px;font-weight: 400;font-size:10px;font-family: Century Gothic'>" + date + ".</span>";
        mailContent += "</div>";
        mailContent += "<h6 style='color: #343a40;margin-left: 10px;font-family: Century Gothic;margin-top: -3px;font-weight: 400;font-size: 11px;'>Cet email est une notification automatique, merci de ne pas répondre à ce message. Connectez-vous sur la plateforme Site Transfert [ D9 ] pour retrouver tous les sites auxquels vous êtes affectés.</h6>";
        return mailContent;
    }

}
