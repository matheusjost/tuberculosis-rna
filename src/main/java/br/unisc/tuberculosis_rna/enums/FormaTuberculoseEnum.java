package br.unisc.tuberculosis_rna.enums;

import br.unisc.tuberculosis_rna.exception.TuberculosisRNAException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaTuberculoseEnum {
    PULMONAR(1, new double[]{0.0, 0.0}),
    EXTRAPULMONAR(2, new double[]{0.0, 1.0}),
    PULMONAR_E_EXTRAPULMONAR(3, new double[]{1.0, 0.0});

    @JsonValue
    private final int valor;
    private final double[]  probabilidade;

    @JsonCreator
    public static FormaTuberculoseEnum fromInt(int valor) {
        for (FormaTuberculoseEnum forma : FormaTuberculoseEnum.values()) {
            if (forma.getValor() == valor) {
                return forma;
            }
        }
        throw new TuberculosisRNAException(
            "Valor inválido para FormaTuberculoseEnum: " + valor + ". Valores válidos: 1 (PULMONAR), 2 (EXTRAPULMONAR), 3 (PULMONAR_E_EXTRAPULMONAR)."
        );
    }
}
