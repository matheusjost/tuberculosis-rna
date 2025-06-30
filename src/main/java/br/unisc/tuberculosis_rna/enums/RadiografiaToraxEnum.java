package br.unisc.tuberculosis_rna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RadiografiaToraxEnum {
    SUSPEITO(1, new double[]{0.0, 0.0}),
    NORMAL(2, new double[]{0.0, 1.0}),
    OUTRA_PATHOLOGIA(3, new double[]{1.0, 0.0}),
    NAO_REALIZADO(4, new double[]{1.0, 1.0});

    private final int valor;
    private final double[]  probabilidade;

    public static RadiografiaToraxEnum fromInt(int valor) {
        for (RadiografiaToraxEnum radiografia : RadiografiaToraxEnum.values()) {
            if (radiografia.getValor() == valor) {
                return radiografia;
            }
        }
        return NAO_REALIZADO;
    }
}
