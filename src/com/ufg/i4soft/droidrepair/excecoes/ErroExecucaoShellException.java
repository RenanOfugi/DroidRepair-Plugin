package com.ufg.i4soft.droidrepair.excecoes;

public class ErroExecucaoShellException extends RuntimeException {

    public ErroExecucaoShellException(final String campo) {
        super(campo);
    }
}
