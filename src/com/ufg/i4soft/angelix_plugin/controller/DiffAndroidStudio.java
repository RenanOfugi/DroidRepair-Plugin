package com.ufg.i4soft.angelix_plugin.controller;

public class DiffAndroidStudio {

    public void executeDiff(String caminho_androidStudio, String arquivo_antes, String arquivo_depois){

        ExecuteShell shell = new ExecuteShell();

        String command = "./" + caminho_androidStudio + " diff " + arquivo_antes + arquivo_depois;

        shell.executeCommand(command);
    }
}
