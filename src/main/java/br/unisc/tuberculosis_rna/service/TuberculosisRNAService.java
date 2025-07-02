package br.unisc.tuberculosis_rna.service;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;
import br.unisc.tuberculosis_rna.enums.*;
import br.unisc.tuberculosis_rna.exception.TuberculosisRNAException;
import br.unisc.tuberculosis_rna.pojo.TuberculosisRNADTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.unisc.tuberculosis_rna.utils.ReaderUtils.readerParseInt;

@Slf4j
@Service
@RequiredArgsConstructor
public class TuberculosisRNAService {

    private static Backpropagation redeNeural = null;
    private final Integer OUTPUT_LAYER_SIZE = 1;
    private final Integer INPUT_LAYER_SIZE = 27;

    private final String DEFAULT_CSV_HEADER = "descrIdade,CS_SEXO,CS_RACA,CS_ZONA,TRATAMENTO,RAIOX_TORA,FORMA,AGRAVAIDS,AGRAVALCOO,AGRAVDIABE,AGRAVDOENC,BACILOSC_E,CULTURA_ES,DT_INIC_TR,SITUA_ENCE,DT_ENCERRA,TEMPO_CURA";

    private final ArquivoService arquivoService;

    public void treinarModelo(String encryptedFilePath,
                                int numCamadas,
                                int tamCamada,
                                double taxaAprendizado,
                                double margemErro,
                                int numInteracoes) {
        log.info("Treinamento do modelo de Tuberculose RNA iniciado.");
        int[] camadas = new int[numCamadas];
        Arrays.fill(camadas, tamCamada);

        redeNeural = new Backpropagation(INPUT_LAYER_SIZE, OUTPUT_LAYER_SIZE, camadas);
        redeNeural.SetLearningRate(taxaAprendizado);
        redeNeural.SetErrorRate(margemErro);
        redeNeural.SetMaxIterationNumber(numInteracoes);

        DataSet dadosTreino = new DataSet(INPUT_LAYER_SIZE, OUTPUT_LAYER_SIZE);

        try {
            List<TuberculosisRNADTO> listTreino = parseCSVTuberculosis(encryptedFilePath);
            for (TuberculosisRNADTO treino : listTreino)
                dadosTreino.Add(new DataSetObject(treino.getEntradaNeuronio(), treino.getSaidaNeuronio()));

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
            double[] result = redeNeural.Recognize(data.getEntradaNeuronio());
            data.setSaidaRecognize(result);
            data.setTempoCura((int) Math.round(result[0] * 10000));
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

    public List<TuberculosisRNADTO> parseCSVTuberculosis(String encryptedFilePath) {
        String filePath = arquivoService.decryptFilePath(encryptedFilePath);

        File file = new File(filePath);
        if (!file.exists())
            throw new TuberculosisRNAException("Arquivo não encontrado: " + filePath);

        List<TuberculosisRNADTO> tuberculosisDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    if (!line.startsWith(DEFAULT_CSV_HEADER))
                        throw new TuberculosisRNAException("Formato do arquivo CSV inválido.");
                    continue;
                }

                String[] values = line.split(",");
                tuberculosisDataList.add(
                        TuberculosisRNADTO.builder()
                                .idade(readerParseInt(values[0]))
                                .sexo(SexoEnum.fromString(values[1].trim()))
                                .raca(RacaEnum.fromInt(readerParseInt(values[2])))
                                .zona(ZonaEnum.fromInt(readerParseInt(values[3])))
                                .tipoEntrada(TipoEntradaEnum.fromInt(readerParseInt(values[4])))
                                .radiografiaTorax(RadiografiaToraxEnum.fromInt(readerParseInt(values[5])))
                                .formaTuberculose(FormaTuberculoseEnum.fromInt(readerParseInt(values[6])))
                                .agravanteAIDS(AgravanteEnum.fromInt(readerParseInt(values[7])))
                                .agravanteAlcoolismo(AgravanteEnum.fromInt(readerParseInt(values[8])))
                                .agravanteDiabetes(AgravanteEnum.fromInt(readerParseInt(values[9])))
                                .agravanteDoencaMental(AgravanteEnum.fromInt(readerParseInt(values[10])))
                                .baciloscopia(BasciloscopiaEnum.fromInt(readerParseInt(values[11])))
                                .culturaEscarro(CulturaEscarroEnum.fromInt(readerParseInt(values[12])))
                                .tempoCura(readerParseInt(values[16]))
                                .build()
                );
            }

            return tuberculosisDataList;
        } catch (Exception e) {
            throw new TuberculosisRNAException("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

}
