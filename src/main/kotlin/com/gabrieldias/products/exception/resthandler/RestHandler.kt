package com.gabrieldias.products.exception.resthandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class RestHandler {

    @ExceptionHandler(value = [Exception::class])
    fun resourceNotFoundException(ex: Exception, request: WebRequest?): ResponseEntity<ErrorMessage> {
        val message = ErrorMessage(
            ex.message,
            ex.stackTrace.contentToString()
        )
        return ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}