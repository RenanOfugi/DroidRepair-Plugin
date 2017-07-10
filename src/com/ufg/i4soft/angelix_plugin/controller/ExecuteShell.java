package com.ufg.i4soft.angelix_plugin.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.angelix_plugin.excecoes.ErroExecucaoShellException;
import com.ufg.i4soft.angelix_plugin.excecoes.FalhaEsperaException;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

class ExecuteShell {

    private static final Logger log = Logger.getLogger(ExecuteShell.class.getName());

    void executeCommand(String command) {

        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);

        BufferedReader reader = null;

       // StringBuffer output = new StringBuffer();

        //Process process;

        try {

            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);

          /*  process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));*/

            String line;

            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

        } catch (IOException e) {

            Messages.showMessageDialog( "Erro ao tentar executar o comando no Angelix", "Comando Inválido", Messages.getErrorIcon());
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando no Angelix");

        }finally {
            secureClose(reader);
        }

        /*catch (InterruptedException e) {

            Messages.showMessageDialog( "Tempo excedido de espera para execução do comando", "Crash!", Messages.getErrorIcon());
            throw new FalhaEsperaException("Tempo excedido de espera para execução do comando");
        }*/

        //return output.toString();
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }
}
