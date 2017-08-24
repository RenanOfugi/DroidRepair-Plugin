package com.ufg.i4soft.angelix_plugin.excecoes;

public class ParametrosVaziosException extends RuntimeException {

    public ParametrosVaziosException(final String campo) {
        super(campo);
    }
}
