package com.ufg.i4soft.angelix_plugin.excecoes;

public class ErroExecucaoShellException extends RuntimeException {

    public ErroExecucaoShellException(final String campo) {
        super(campo);
    }
}
