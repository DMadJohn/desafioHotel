package br.com.misc.converters;

import br.com.database.guest.GuestDef;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component("GuestDefToString")
@Converter(autoApply = true)
@ConfigurationPropertiesBinding
public class JpaConverterGuestDefJson implements AttributeConverter<br.com.database.guest.GuestDef, java.lang.String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    //@Override
    public String convertToDatabaseColumn(GuestDef guestDef) {
        if (guestDef == null) return null;

        try {
            return objectMapper.writeValueAsString(guestDef);
        } catch (Throwable any) {
            any.printStackTrace();
            return null;
        }
    }

    //@Override
    public GuestDef convertToEntityAttribute(String string) {
        if (string.trim().length() == 0) return null;

        try {
            return objectMapper.readValue(string, GuestDef.class);
        } catch(Throwable any) {
            any.printStackTrace();
            return null;
        }
    }
}
