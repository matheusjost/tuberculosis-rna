package br.unisc.tuberculosis_rna.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum IdadeEnum {
    MENOS_DE_25(0, 25, "Menos de 25 anos", new double[]{0.0, 0.0}),
    ENTRE_25_E_50(26, 50, "Entre 25 e 50 anos", new double[]{0.0, 1.0}),
    ENTRE_50_E_80(51, 80, "Entre 50 e 80 anos", new double[]{1.0, 0.0}),
    MAIS_80(81, 120, "Mais de 80 anos", new double[]{1.0, 1.0});

    private final int       min;
    private final int       max;
    @JsonValue
    private final String    desc;
    private final double[]  probabilidade;

    @JsonCreator
    public static IdadeEnum fromAnos(int anos) {
        for (IdadeEnum idadeEnum : IdadeEnum.values()) {
            if (anos >= idadeEnum.getMin() && anos <= idadeEnum.getMax()) {
                return idadeEnum;
            }
        }
        return null;
    }

    public static IdadeEnum fromProbabilidade(double[] probabilidade) {
        for (IdadeEnum idadeEnum : IdadeEnum.values()) {
            if (Arrays.equals(idadeEnum.getProbabilidade(), probabilidade))
                return idadeEnum;
        }
        return null;
    }
}
