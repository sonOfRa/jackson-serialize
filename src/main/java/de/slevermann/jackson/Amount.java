package de.slevermann.jackson;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Amount {

    @JsonValue
    @Precision(2)
    private BigDecimal amount;

    @JsonCreator
    public Amount(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.UNNECESSARY);
    }
}
