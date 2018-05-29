package br.com.misc.converters;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component("CalendarToISO")
@Converter(autoApply = true)
@ConfigurationPropertiesBinding
public class JpaConverterISODateCalendar implements AttributeConverter<java.util.Calendar, java.lang.String> {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    //@Override
    public String convertToDatabaseColumn(Calendar calendar) {
        return formatter.format(Calendar.getInstance().getTime());
    }

    //@Override
    public Calendar convertToEntityAttribute(String s) {
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(formatter.parse(s));
            return date;
        } catch (Throwable any) {
            any.printStackTrace();
            return null;
        }
    }
}
