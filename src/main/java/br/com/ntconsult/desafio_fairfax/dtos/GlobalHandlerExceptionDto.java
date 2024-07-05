package br.com.ntconsult.desafio_fairfax.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GlobalHandlerExceptionDto {

    public GlobalHandlerExceptionDto(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
