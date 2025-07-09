package br.unisc.tuberculosis_rna.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ZonaEnum {
    URBANA(1, new double[]{0.0, 0.0}),
    RURAL(2, new double[]{0.0, 1.0}),
    PERIURBANA(3, new double[]{1.0, 0.0}),
    IGNORADO(9, new double[]{1.0, 1.0});

    @JsonValue
    private final int valor;
    private final double[]  probabilidade;

    @JsonCreator
    public static ZonaEnum fromInt(int valor) {
        for (ZonaEnum zona : ZonaEnum.values()) {
            if (zona.getValor() == valor) {
                return zona;
            }
        }
        return IGNORADO;
    }
}
