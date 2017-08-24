package com.ufg.i4soft.angelix_plugin.excecoes;

public class ArquivoNaoAbre extends RuntimeException {

    public ArquivoNaoAbre(final String campo){ super(campo);}
}
