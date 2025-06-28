package br.unisc.tuberculosis_rna.controller;

import br.unisc.tuberculosis_rna.pojo.ApiResponseDTO;
import br.unisc.tuberculosis_rna.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arquivo")
public class ArquivoController extends TuberculosisRNAController {

    private final ArquivoService arquivoService;

    @Override
    @Operation(summary = "Upload de arquivo")
    public ResponseEntity<ApiResponseDTO> upload(@RequestParam("file") MultipartFile file) {
        try {
            String path = arquivoService.armazenarArquivo(file);
            //TODO: hash filePath to return it

            return ResponseHandler.createdApiResponse(1);
        } catch (Exception e) {
            return ResponseHandler.errorApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
