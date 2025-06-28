package br.unisc.tuberculosis_rna.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TuberculosisRNAService {

    public void treinarModelo(String hashFile,
                                int numCamadas,
                                int tamCamada,
                                double taxaAprendizado,
                                int margemErro) {
        log.info("Treinamento do modelo de Tuberculose RNA iniciado.");
        log.info("Treinamento do modelo de Tuberculose RNA concluído.");
    }

}
