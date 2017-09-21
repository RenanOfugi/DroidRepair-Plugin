package com.ufg.i4soft.droidrepair.excecoes;

public class ArquivoNaoEncontrado extends RuntimeException {

    public ArquivoNaoEncontrado(final String campo) {
        super(campo);
    }
}
