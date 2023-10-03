package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NotificationResponse<T> {

    private List<T> list;
    private Integer count;

    public NotificationResponse(List<T> list) {
        this.list = list;
        this.count = list.size();
    }

    public NotificationResponse(Integer count) {
        this.list = null;
        this.count = count;
    }
}
