package br.unisc.tuberculosis_rna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TesteTuberculoseEnum {
    NAO_REATOR(1, new double[]{0.0, 0.0}),
    REATOR_FRACO(2, new double[]{0.0, 1.0}),
    REATOR_FORTE(3, new double[]{1.0, 0.0}),
    NAO_REALIZADO(4, new double[]{1.0, 1.0});

    private final int valor;
    private final double[]  probabilidade;

    public static TesteTuberculoseEnum fromInt(int valor) {
        for (TesteTuberculoseEnum teste : TesteTuberculoseEnum.values()) {
            if (teste.getValor() == valor) {
                return teste;
            }
        }
        return NAO_REALIZADO;
    }
}
