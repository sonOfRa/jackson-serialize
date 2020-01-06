package de.slevermann.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FixedDigitSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {
    private Integer precision;

    public FixedDigitSerializer() {
        this(null);
    }

    public FixedDigitSerializer(Integer precision) {
        this.precision = precision;
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        BigDecimal rounded;
        if (precision == null) {
            rounded = value;
        } else {
            rounded = value.setScale(precision, RoundingMode.HALF_UP);
        }
        gen.writeNumber(rounded);
    }

    @Override
    public JsonSerializer<BigDecimal> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Precision precision = property.getAnnotation(Precision.class);
        if (precision == null) {
            return this;
        }
        return new FixedDigitSerializer(precision.value());
    }
}
