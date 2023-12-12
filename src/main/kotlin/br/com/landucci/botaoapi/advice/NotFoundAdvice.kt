package br.com.landucci.botaoapi.advice

import br.com.landucci.botaoapi.exception.BotaoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class BotaoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BotaoNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun grupoNotFoundHandler(ex: BotaoNotFoundException): String? {
        return ex.message
    }
}