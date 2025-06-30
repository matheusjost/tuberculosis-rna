package br.unisc.tuberculosis_rna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AgravanteEnum {
    SIM(1, new double[]{0.0, 0.0}),
    NAO(2, new double[]{0.0, 1.0}),
    IGNORADO(9, new double[]{1.0, 0.0});

    private final int valor;
    private final double[]  probabilidade;

    public static AgravanteEnum fromInt(int valor) {
        for (AgravanteEnum agravante : AgravanteEnum.values()) {
            if (agravante.getValor() == valor) {
                return agravante;
            }
        }
        return IGNORADO;
    }

}
