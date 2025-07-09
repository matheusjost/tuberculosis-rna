package br.unisc.tuberculosis_rna.controller;

import br.unisc.tuberculosis_rna.pojo.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseHandler {
    public static ResponseEntity<ApiResponseDTO> okApiResponse() {
        return ResponseEntity.ok().body(ApiResponseDTO.okApiResponse());
    }

    public static ResponseEntity<ApiResponseDTO> okApiResponse(Object data) {
        return ResponseEntity.ok().body(ApiResponseDTO.okApiResponse(data));
    }

    public static ResponseEntity<ApiResponseDTO> okApiResponse(Object data, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponseDTO.okApiResponse(data, status));
    }

    public static ResponseEntity<byte[]> okApiResponse(String mt, byte[] file) {
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mt)).body(file);
    }

    public static ResponseEntity<ApiResponseDTO> createdApiResponse(Object id) {
        return ResponseEntity.created(URI.create(id.toString())).body(ApiResponseDTO.okApiResponse(id, HttpStatus.CREATED));
    }

    public static ResponseEntity<ApiResponseDTO> errorApiResponse(HttpStatus status, String error, String path) {
        return ResponseEntity.status(status).body(ApiResponseDTO.errorApiResponse(status, error, path));
    }

    public static ResponseEntity<ApiResponseDTO> errorApiResponse(HttpStatus status, String error) {
        return ResponseEntity.status(status).body(ApiResponseDTO.errorApiResponse(status, error));
    }

    public static ResponseEntity<ApiResponseDTO> errorNotFoundApiResponse() {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<ApiResponseDTO> errorBadRequestApiResponse(String error) {
        return ResponseEntity.badRequest().body(ApiResponseDTO.errorApiResponse(HttpStatus.BAD_REQUEST, error));
    }
}
