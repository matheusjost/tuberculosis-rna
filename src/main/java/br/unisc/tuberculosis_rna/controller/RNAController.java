package br.unisc.tuberculosis_rna.controller;

import br.unisc.tuberculosis_rna.command.TuberculosisRNACommand;
import br.unisc.tuberculosis_rna.pojo.ApiResponseDTO;
import br.unisc.tuberculosis_rna.pojo.TuberculosisRNADTO;
import br.unisc.tuberculosis_rna.service.TuberculosisRNAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rna")
public class RNAController  extends TuberculosisRNAController<TuberculosisRNACommand> {

    private final TuberculosisRNAService tuberculosisRNAService;

    @Override
    public ResponseEntity<ApiResponseDTO> post(@RequestBody TuberculosisRNACommand requestBody) {
        try {
            tuberculosisRNAService.treinarModelo(requestBody.encryptedFilePath(), requestBody.numCamadas(), requestBody.tamCamada(), requestBody.taxaAprendizado(), requestBody.margemErro(), requestBody.numInteracoes());
            return ResponseHandler.okApiResponse("Modelo treinado com sucesso.");
        } catch (Exception e) {
            return ResponseHandler.errorApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/recognize")
    public ResponseEntity<ApiResponseDTO> recognize(@RequestBody TuberculosisRNADTO requestBody) {
        try {
            return ResponseHandler.okApiResponse(tuberculosisRNAService.reconhecer(requestBody));
        } catch (Exception e) {
            return ResponseHandler.errorApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
