package com.example.demo.controller;

import com.example.demo.dao.BadRequestException;
import com.example.demo.dto.ResponceErrorDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

    // вказую, щоб оброблялась помилка MethodArgumentNotValidException і повертала лише певні поля
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponceErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = parceValidationException(ex);
        return new ResponceErrorDTO(LocalDateTime.now(), message, HttpStatus.BAD_REQUEST.value());
    }

    // цей метод який поверне помилку у такому вигляді як я хочу (в даному випадку це дефолтне повідомлення)
// результат прокидається вище.
    private String parceValidationException(MethodArgumentNotValidException ex) {
        List<String> collect = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return collect.toString();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponceErrorDTO handleBadRequestException(BadRequestException ex) {
        return new ResponceErrorDTO(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

