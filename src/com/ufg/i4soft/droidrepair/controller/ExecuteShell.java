package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.excecoes.ErroExecucaoShellException;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

class ExecuteShell {

    private static final Logger log = Logger.getLogger(ExecuteShell.class.getName());

    String executeCommand(String command) {

        final ArrayList<String> commands = new ArrayList<>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);

        BufferedReader reader = null;

        String lastLineText = null;

        try {

            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);


            String line;

            while ((line = reader.readLine()) != null){
                System.out.println(line);
                lastLineText = line;
            }

        } catch (IOException e) {

            Messages.showMessageDialog( "Erro ao tentar executar o comando no Angelix", "Comando Inválido", Messages.getErrorIcon());
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando no Angelix");

        }finally {
            secureClose(reader);
        }

        return lastLineText;
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
