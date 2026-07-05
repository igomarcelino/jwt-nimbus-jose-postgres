package com.github.igomarcelino.jwt_nimbus_jose.global_exection_handler;

import com.github.igomarcelino.jwt_nimbus_jose.service.aviso.exceptions.AvisoInvalidException;
import com.github.igomarcelino.jwt_nimbus_jose.service.aviso.exceptions.AvisoNotFoundException;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.exceptions.PessoaInvalidException;
import com.github.igomarcelino.jwt_nimbus_jose.service.pessoa.exceptions.PessoaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseError responseError(String cause, Exception e, HttpStatus status){
        return new ResponseError(
                cause,
                e.getMessage(),
                status.value(),
                new Timestamp(System.currentTimeMillis())
        );
    }

    @ExceptionHandler(PessoaNotFoundException.class)
    private ResponseEntity<ResponseError> handlePessoaNotFoundException(PessoaNotFoundException e) {
        logger.warn("Causa: [ Pessoa ] - Mensagem: [ {} ]", e.getMessage());
        var responseErrror = responseError("Pessoa: ", e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseErrror, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PessoaInvalidException.class)
    private ResponseEntity<ResponseError> handlePessoaInvalidException(PessoaInvalidException e) {
        logger.warn("Causa: [ Pessoa ] - Mensagem: [ {} ]", e.getMessage());
        var responseErrror = responseError("Pessoa: ", e, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(responseErrror, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DefaultValueNotFoundException.class)
    private ResponseEntity<ResponseError> handleDefaultValueNotFoundExceptionException(DefaultValueNotFoundException e) {
        logger.warn("Causa: [ DefaultException ] - Mensagem: [ {} ]", e.getMessage());
        var responseErrror = responseError("DefaultException: ", e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseErrror, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AvisoNotFoundException.class)
    private ResponseEntity<ResponseError> handleAvisoFoundException(AvisoNotFoundException e) {
        logger.warn("Causa: [ Aviso ] - Mensagem: [ {} ]", e.getMessage());
        var responseErrror = responseError("Aviso: ", e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseErrror, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AvisoInvalidException.class)
    private ResponseEntity<ResponseError> handlePessoaInvalidException(AvisoInvalidException e) {
        logger.warn("Causa: [ Aviso ] - Mensagem: [ {} ]", e.getMessage());
        var responseErrror = responseError("Aviso: ", e, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(responseErrror, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    //Globais

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.warn("Causa: [ Method ] - Mensagem: [ {} ]", e.getMessage());
        var responseError = new ResponseError(
                "Metodo"
                ,e.getBindingResult().getFieldErrors().get(0).getDefaultMessage()
                ,400
                ,Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ResponseError> handleAuthorizationDeniedException(AuthorizationDeniedException e){
        logger.error("Causa: [ Acesso ] - Mensagem: [ {} ]",e.getMessage());
        var responseError = new ResponseError(
                "Acesso"
                ,"Acesso Negado"
                ,403
                ,Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(responseError,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGenircException(Exception e){
        logger.error("Causa: [ Erro interno ] - Mensagem: [ {} ]", e.getMessage());
        var responseError = responseError("Erro interno", e, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
