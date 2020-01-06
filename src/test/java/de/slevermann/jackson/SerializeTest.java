package de.slevermann.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SerializeTest {

    @Test
    public void testSerialize() throws JsonProcessingException {
        Amount amount = new Amount(new BigDecimal("1.23"));

        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new FixedDigitSerializer());
        om.registerModule(module);

        System.out.println(om.writeValueAsString(amount));
    }
}
