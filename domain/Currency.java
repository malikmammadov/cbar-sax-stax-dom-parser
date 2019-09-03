package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Currency {

    private String code;
    private String nominal;
    private String name;
    private BigDecimal value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    private DecimalFormat decimalFormat = new DecimalFormat("0.0000");

    @Override
    public String toString() {
        return String.format("%45s %20s %20s %20s", name, nominal, code, decimalFormat.format(value));
    }
}