package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class EntityResponse<T> implements Serializable {

    private final T body;
    private boolean success;
    private String message;

    public EntityResponse(T body) {
        this.success = true;
        this.body = body;
        this.message = "Opération effectuée";
    }

    public EntityResponse(T body, String message) {
        this.success = true;
        this.body = body;
        this.message = message;
    }

    public EntityResponse(boolean success, String message) {
        this.success = success;
        this.body = null;
        this.message = message;
    }

    public EntityResponse(String message) {
        this.success = false;
        this.body = null;
        this.message = message;
    }

    public EntityResponse(T body, boolean success, String message) {
        this.body = body;
        this.success = success;
        this.message = message;
    }

    public EntityResponse() {
        body = null;
    }
}
