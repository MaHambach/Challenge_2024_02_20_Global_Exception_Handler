package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage(
        String apiPath,
        HttpStatus errorCode,
        String errorMsg,
        LocalDateTime errorTime
) {
}
