package com.ufg.i4soft.angelix_plugin.controller;

import com.ufg.i4soft.angelix_plugin.excecoes.ErroExecucaoShellException;
import com.ufg.i4soft.angelix_plugin.excecoes.FalhaEsperaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShell {

    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process process;

        try {

            process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                output.append(line).append("\n");
            }

        } catch (IOException e) {
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando no Angelix");
        } catch (InterruptedException e) {
            throw new FalhaEsperaException("Tempo excedido de espera para execução do comando");
        }

        return output.toString();
    }
}
