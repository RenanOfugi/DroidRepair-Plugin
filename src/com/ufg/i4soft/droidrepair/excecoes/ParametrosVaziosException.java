package com.ufg.i4soft.droidrepair.excecoes;

public class ParametrosVaziosException extends RuntimeException {

    public ParametrosVaziosException(final String campo) {
        super(campo);
    }
}
