package br.com.dbserver.desafio.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime localDateTime, String message, String details) {
}
