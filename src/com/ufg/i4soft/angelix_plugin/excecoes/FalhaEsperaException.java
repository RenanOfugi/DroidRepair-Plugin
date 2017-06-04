package com.ufg.i4soft.angelix_plugin.excecoes;

public class FalhaEsperaException extends RuntimeException {

    public FalhaEsperaException(final String campo) {
        super(campo);
    }
}
