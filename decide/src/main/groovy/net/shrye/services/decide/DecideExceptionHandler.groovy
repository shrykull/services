package net.shrye.services.decide

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class DecideExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = [IllegalArgumentException])
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}