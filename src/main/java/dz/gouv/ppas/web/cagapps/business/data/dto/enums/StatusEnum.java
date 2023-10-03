package dz.gouv.ppas.web.cagapps.business.data.dto.enums;

public enum StatusEnum {
    EN_COURS("En Cours"),
    EN_ATTENTE("En Attente"),
    ENVOYER("Envoyer"),
    COLTURER("Cloturer"),
    ANNULER("Annuler"),
    DATE_ANNULER("Date Annuler"),
    MODIFIER("Modification"),
    FILES_DOWN("Documents Télécharger"),
    INVITATION_SEND("Invitation Envoyés"),
    METTING_STARTED("Réunion Démarrer"),
    PV_FILE_ADD("PV Ajouter"),
    PLANIFIER("Planifier");

    private String name = "";

    StatusEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
