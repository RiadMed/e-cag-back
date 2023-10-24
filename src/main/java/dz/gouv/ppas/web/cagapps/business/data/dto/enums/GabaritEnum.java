package dz.gouv.ppas.web.cagapps.business.data.dto.enums;

public enum GabaritEnum {
    C000("C000"),
    C001("C001"),
    C002("C002"),
    C003("C003"),
    C004("C004"),
    C005("C005");

    private String name = "";

    GabaritEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
