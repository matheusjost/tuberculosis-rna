package br.unisc.tuberculosis_rna.service;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;
import br.unisc.tuberculosis_rna.enums.TempoCuraEnum;
import br.unisc.tuberculosis_rna.exception.TuberculosisRNAException;
import br.unisc.tuberculosis_rna.pojo.TuberculosisRNADTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class TuberculosisRNAService {

    private static Backpropagation redeNeural = null;
    private final Integer OUTPUT_LAYER_SIZE = 2;
    private final Integer INPUT_LAYER_SIZE = 26;

    public void treinarModelo(String hashFile,
                                int numCamadas,
                                int tamCamada,
                                double taxaAprendizado,
                                int margemErro,
                                int numInteracoes) {
        log.info("Treinamento do modelo de Tuberculose RNA iniciado.");
        int[] camadas = new int[numCamadas];
        Arrays.fill(camadas, tamCamada);

        redeNeural = new Backpropagation(INPUT_LAYER_SIZE, OUTPUT_LAYER_SIZE, camadas);
        redeNeural.SetLearningRate(taxaAprendizado);
        redeNeural.SetErrorRate(margemErro);
        redeNeural.SetMaxIterationNumber(numInteracoes);

        DataSet dadosTreino = new DataSet(INPUT_LAYER_SIZE, OUTPUT_LAYER_SIZE);

        // TODO: Ler o arquivo e popular a variável dadosTreino.

        try {
            redeNeural.Learn(dadosTreino);
        } catch (Exception e) {
            throw new TuberculosisRNAException("Erro ao treinar a RNA: " + e.getMessage());
        }
        log.info("Treinamento do modelo de Tuberculose RNA concluído.");
    }

    public TuberculosisRNADTO reconhecer(TuberculosisRNADTO data) {
        if (redeNeural == null)
            throw new TuberculosisRNAException("Rede neural não treinada.");

        try {
            double[] result = parseRecognize(redeNeural.Recognize(data.getEntradaNeuronio()));

            data.setTempoCura(TempoCuraEnum.fromProbabilidade(result));
            return data;
        } catch (Exception e) {
            throw new TuberculosisRNAException("Erro ao reconhecer: " + e.getMessage());
        }
    }

    private double[] parseRecognize(double[] recognize) {
        double[] result = new double[OUTPUT_LAYER_SIZE];
        for (int i = 0; i < OUTPUT_LAYER_SIZE; i++) {
            if (Math.round(recognize[i]) <= 0.5) {
                result[i] = 0.0;
            } else {
                result[i] = 1.0;
            }
        }

        return result;
    }

}
