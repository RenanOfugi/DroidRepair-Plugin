package com.ufg.i4soft.angelix_plugin.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.angelix_plugin.excecoes.ParametrosVaziosException;

import java.util.ArrayList;

public class ManageAngelix {

    public void runAngelix(ArrayList<String> args){

        verifyInputs(args);
    }

    private void verifyInputs(ArrayList<String> args) {

        boolean parametrosNaoVazios = true;

        for (String arg : args) {
            if (arg == null) {
                parametrosNaoVazios = false;
            }
        }

        if (parametrosNaoVazios) {
            executeAngelix(args);
        } else {
            Messages.showMessageDialog("Não foi identificado nenhuma entrada de parâmetros", "Parâmetro Inválido", Messages.getErrorIcon());
            throw new ParametrosVaziosException("Não foi possível executar o Angelix: Está faltando parâmetros necessários");
        }
    }

    private void executeAngelix(ArrayList<String> args) {

        ExecuteShell shell = new ExecuteShell();

        String command = "angelix " + args.get(0) + " " + args.get(1) + " " + args.get(2);

        String output = shell.executeCommand(command);

        System.out.println(output);
    }

}
