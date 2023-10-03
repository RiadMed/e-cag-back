package dz.gouv.ppas.web.cagapps.business.data.dto.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.Base64Utils;

public class BytesDeserializer extends JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(JsonParser jsonparser, DeserializationContext arg1) {
        try {
            String data = jsonparser.getText();
            return Base64Utils.decodeFromString(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
