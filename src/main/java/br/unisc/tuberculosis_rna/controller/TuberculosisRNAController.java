package br.unisc.tuberculosis_rna.controller;

import br.unisc.tuberculosis_rna.pojo.ApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public abstract class TuberculosisRNAController<T> {

    @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @PostMapping()
    public ResponseEntity<ApiResponseDTO> post(@RequestBody T requestBody) {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }

    @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @PostMapping("upload")
    public ResponseEntity<ApiResponseDTO> upload(@RequestParam("file") MultipartFile file) {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }

    @ApiResponse(responseCode = "200", description = "Sucesso ao carrergar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDTO> get(@PathVariable String id) {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }

    @ApiResponse(responseCode = "200", description = "Sucesso ao carrergar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO> listAll() {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }

    @ApiResponse(responseCode = "200", description = "Sucesso ao deletar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable String id) {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }

    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Proibido")
    @ApiResponse(responseCode = "501", description = "Não implementado")
    @Operation(summary = "Não implementado")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDTO> put(@PathVariable String id, @RequestBody T requestBody) {
        return ResponseHandler.errorApiResponse(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet");
    }
}
