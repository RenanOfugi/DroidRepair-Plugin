package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.excecoes.ErroExecucaoShellException;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShellThread implements Runnable {

    @NotNull
    private String command;

    @NotNull
    private boolean sudo;
    private String password;

    public ExecuteShellThread(String command, boolean sudo, String password) {

        this.command = command;
        this.sudo = sudo;
        this.password = password;
    }

    @Override
    public void run() {

        try {

            Process p;

            if (sudo) {
                p = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", "echo " + password + "| sudo -S " + command});

            } else {
                p = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (IOException e) {

            Messages.showMessageDialog("Erro ao tentar executar o comando", "Comando Inválido", Messages.getErrorIcon());
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando");

        }
    }
}
