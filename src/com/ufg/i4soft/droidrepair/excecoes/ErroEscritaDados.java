package com.ufg.i4soft.droidrepair.excecoes;

public class ErroEscritaDados extends RuntimeException{

    public ErroEscritaDados(final String campo){super(campo);}
}
