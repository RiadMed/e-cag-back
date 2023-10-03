package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class JwtResponse<T> implements Serializable {

    private UUID idAccess;
    private final T token;
    private boolean success;
    private String message;

    public JwtResponse(UUID idAccess, T token) {
        this.success = true;
        this.idAccess = idAccess;
        this.token = token;
        this.message = "Bienvenu au E-CAG";
    }

    public JwtResponse(T token) {
        this.success = true;
        this.idAccess = null;
        this.token = token;
        this.message = "Bienvenu au E-CAG";
    }

    public JwtResponse(boolean success, String message) {
        this.success = success;
        this.idAccess = null;
        this.token = null;
        this.message = message;
    }

}