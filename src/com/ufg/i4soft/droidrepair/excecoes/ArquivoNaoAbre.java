package com.ufg.i4soft.droidrepair.excecoes;

public class ArquivoNaoAbre extends RuntimeException {

    public ArquivoNaoAbre(final String campo){ super(campo);}
}
