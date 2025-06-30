package br.unisc.tuberculosis_rna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEntradaEnum {
    CASO_NOVO(1, new double[]{0.0, 0.0, 0.0}),
    RECIDIVA(2, new double[]{0.0, 0.0, 1.0}),
    REINGRESSO_APOS_ABANDONO(3, new double[]{0.0, 1.0, 0.0}),
    NAO_SABE(4, new double[]{0.0, 1.0, 1.0}),
    TRANSFERENCIA(5, new double[]{1.0, 0.0, 0.0}),
    POS_OBITO(6, new double[]{1.0, 0.0, 1.0});

    private final int valor;
    private final double[]  probabilidade;

    public static TipoEntradaEnum fromInt(int valor) {
        for (TipoEntradaEnum tipo : TipoEntradaEnum.values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        return NAO_SABE;
    }
}
