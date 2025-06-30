package br.unisc.tuberculosis_rna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexoEnum {
    MASCULINO("M", new double[]{0.0, 0.0}),
    FEMININO("F", new double[]{0.0, 1.0}),
    IGNORADO("I", new double[]{1.0, 0.0});

    private final String valor;
    private final double[] probabilidade;

    public static SexoEnum fromString(String valor) {
        for (SexoEnum sexo : SexoEnum.values()) {
            if (sexo.getValor().equalsIgnoreCase(valor)) {
                return sexo;
            }
        }
        return IGNORADO;
    }
}
