package br.unisc.tuberculosis_rna.pojo;

import br.unisc.tuberculosis_rna.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import static br.unisc.tuberculosis_rna.utils.DoubleUtils.concat;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuberculosisRNADTO {
    private IdadeEnum               idade;
    private SexoEnum                sexo;
    private RacaEnum                raca;
    private ZonaEnum                zona;
    private TipoEntradaEnum         tipoEntrada;
    private RadiografiaToraxEnum    radiografiaTorax;
    private FormaTuberculoseEnum    formaTuberculose;
    private AgravanteEnum           agravanteAIDS;
    private AgravanteEnum           agravanteAlcoolismo;
    private AgravanteEnum           agravanteDiabetes;
    private AgravanteEnum           agravanteDoencaMental;
    private BasciloscopiaEnum       baciloscopia;
    private CulturaEscarroEnum      culturaEscarro;
    private TempoCuraEnum           tempoCura;

    public double[] getEntradaNeuronio() {
        return concat(
                idade.getProbabilidade(),
                sexo.getProbabilidade(),
                raca.getProbabilidade(),
                zona.getProbabilidade(),
                tipoEntrada.getProbabilidade(),
                radiografiaTorax.getProbabilidade(),
                formaTuberculose.getProbabilidade(),
                agravanteAIDS.getProbabilidade(),
                agravanteAlcoolismo.getProbabilidade(),
                agravanteDiabetes.getProbabilidade(),
                agravanteDoencaMental.getProbabilidade(),
                baciloscopia.getProbabilidade(),
                culturaEscarro.getProbabilidade()
        );
    }

    public double[] getSaidaNeuronio() {
        return this.tempoCura.getProbabilidade();
    }
}
