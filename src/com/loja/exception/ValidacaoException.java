package com.loja.exception;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException() {
        super();
    }

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }

    public ValidacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ValidacaoException(Throwable causa) {
        super(causa);
    }
}
