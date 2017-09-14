package com.ufg.i4soft.droidrepair.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecuteShellTest {

    @Test
    public void executeCommand() throws Exception {

        String command = "cd ~/angelix;" + //entrar no diretório para acessar o arquivo "activate"
                ". activate;" + //executar o arquivo "activate"
                "cd ~/angelix/test/enum;" + //mudar o diretorio para o local onde será gerado o patch
                "~/angelix/src/tools/angelix ~/angelix/test/enum/src " + //execução do angelix de fato
                "test.c ~/angelix/test/enum/oracle 1 2 3 --assert ~/angelix/test/enum/assert.json";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(command);

    }

}