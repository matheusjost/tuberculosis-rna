package br.unisc.tuberculosis_rna.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RacaEnum {
    BRANCA(1, new double[]{0.0, 0.0, 0.0}),
    PRETA(2, new double[]{0.0, 0.0, 1.0}),
    AMARELA(3, new double[]{0.0, 1.0, 0.0}),
    PARDA(4, new double[]{0.0, 1.0, 1.0}),
    INDIGENA(5, new double[]{1.0, 0.0, 0.0}),
    IGNORADO(9, new double[]{1.0, 0.0, 1.0});

    @JsonValue
    private final int valor;
    private final double[]  probabilidade;

    @JsonCreator
    public static RacaEnum fromInt(int valor) {
        for (RacaEnum raca : RacaEnum.values()) {
            if (raca.getValor() == valor) {
                return raca;
            }
        }
        return IGNORADO;
    }
}
