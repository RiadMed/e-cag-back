package dz.gouv.ppas.web.cagapps.business.data.dto.enums;

public enum RoleEnum {
    Administrateur("Administrateur"),
    Secretaire("Secretaire"),
    Membre("Membre"),
    Inviter("Inviter");

    private String name = "";

    RoleEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
