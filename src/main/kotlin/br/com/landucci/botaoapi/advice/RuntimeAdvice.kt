package br.com.landucci.botaoapi.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class RuntimeAdvice {

    @ResponseBody
    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun grupoNotFoundHandler(ex: RuntimeException): String? {
        return ex.message
    }
}