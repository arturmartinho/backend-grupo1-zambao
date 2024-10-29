package br.insper.grupo1.exception;

public class CidadeNotFoundException extends RuntimeException {

    public CidadeNotFoundException(String message) {
        super(message);
    }
}
