package br.unisc.tuberculosis_rna.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TempoCuraEnum {
    MENOS_90(0, 90, "Menos de 90 dias", new double[]{0.0, 0.0}),
    ENTRE_90_E_120(91, 120, "Entre 90 e 120 dias", new double[]{0.0, 1.0}),
    ENTRE_120_E_365(121, 365, "Entre 120 e 365 dias", new double[]{1.0, 0.0}),
    MAIS_365(366, Integer.MAX_VALUE, "Mais de 1 ano", new double[]{1.0, 1.0});

    private final int       min;
    private final int       max;
    private final String    desc;
    private final double[]  probabilidade;

    @JsonValue
    public String getDesc() {
        return desc;
    }

    public static TempoCuraEnum fromDias(int dias) {
        for (TempoCuraEnum tempoCura : TempoCuraEnum.values()) {
            if (dias >= tempoCura.getMin() && dias <= tempoCura.getMax()) {
                return tempoCura;
            }
        }
        return null;
    }

    public static TempoCuraEnum fromProbabilidade(double[] probabilidade) {
        for (TempoCuraEnum tempoCura : TempoCuraEnum.values()) {
            if (Arrays.equals(tempoCura.getProbabilidade(), probabilidade))
                return tempoCura;
        }
        return null;
    }
}
