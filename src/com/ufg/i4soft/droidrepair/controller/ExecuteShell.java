package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.excecoes.ErroExecucaoShellException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ExecuteShell {

    private static final Logger log = Logger.getLogger(ExecuteShell.class.getName());

    public String executeCommand(String command) {

        String statusline = "";

        try {

            Process p = Runtime.getRuntime().exec(new String[]{"/bin/bash","-c",command});

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);

                if (line.toLowerCase().contains("success")){

                    statusline = "success";
                } else if (line.toLowerCase().contains("fail")){

                    statusline = "fail";
                }
            }

            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = stderr.readLine()) != null){
                System.out.println(line);
            }
            in.close();

        } catch (IOException e) {

            Messages.showMessageDialog("Erro ao tentar executar o comando", "Comando Inválido", Messages.getErrorIcon());
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando");

        }

        return statusline;
    }

}
