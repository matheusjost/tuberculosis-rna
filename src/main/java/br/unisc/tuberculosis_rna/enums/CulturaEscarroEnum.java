package br.unisc.tuberculosis_rna.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CulturaEscarroEnum {
    POSITIVA(1, new double[]{0.0, 0.0}),
    NEGATIVA(2, new double[]{0.0, 1.0}),
    EM_ANDAMENTO(3, new double[]{1.0, 0.0}),
    NAO_REALIZADA(4, new double[]{1.0, 1.0});

    @JsonValue
    private final int valor;
    private final double[]  probabilidade;

    @JsonCreator
    public static CulturaEscarroEnum fromInt(int valor) {
        for (CulturaEscarroEnum basciloscopia : CulturaEscarroEnum.values()) {
            if (basciloscopia.getValor() == valor) {
                return basciloscopia;
            }
        }
        return NAO_REALIZADA;
    }
}
