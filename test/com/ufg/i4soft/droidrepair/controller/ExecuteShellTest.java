package com.ufg.i4soft.droidrepair.controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ExecuteShellTest {

    @Test
    public void executeCommand() throws Exception {

        String command = "cd ~/angelix;" + //entrar no diretório para acessar o arquivo "activate"
                ". activate;" + //executar o arquivo "activate"
                "cd ~/angelix/tests/enum;" + //mudar o diretorio para o local onde será gerado o patch
                "angelix ~/angelix/tests/enum/src " + //execução do angelix de fato
                "test.c oracle 1 2 3 --assert assert.json";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(command, false, null);

    }

}