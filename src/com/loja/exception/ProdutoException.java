package com.loja.exception;


// Pego no chatGPT, não etendi muito a lógica

public class ProdutoException extends RuntimeException {

    public ProdutoException() {
        super();
    }

    public ProdutoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ProdutoException(Throwable causa) {
        super(causa);
    }
}
