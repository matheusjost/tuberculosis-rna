package br.unisc.tuberculosis_rna.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponseDTO(Date timestamp, int status, String error, String path, Object data) {

    public static ApiResponseDTO okApiResponse() {
        return new ApiResponseDTO(new Date(), HttpStatus.OK.value(), null, null, null);
    }

    public static ApiResponseDTO okApiResponse(Object data) {
        return new ApiResponseDTO(new Date(), HttpStatus.OK.value(), null, null, data);
    }

    public static ApiResponseDTO okApiResponse(HttpStatus status) {
        return new ApiResponseDTO(new Date(), status.value(), null, null, null);
    }

    public static ApiResponseDTO okApiResponse(Object data, HttpStatus status) {
        return new ApiResponseDTO(new Date(), status.value(), null, null, data);
    }

    public static ApiResponseDTO errorApiResponse(HttpStatus status, String error, String path) {
        return new ApiResponseDTO(new Date(), status.value(), error, path, null);
    }

    public static ApiResponseDTO errorApiResponse(HttpStatus status, String error) {
        return new ApiResponseDTO(new Date(), status.value(), error, null, null);
    }

    public static ApiResponseDTO errorApiResponse(HttpStatus status, String error, Object data) {
        return new ApiResponseDTO(new Date(), status.value(), error, null, data);
    }

}