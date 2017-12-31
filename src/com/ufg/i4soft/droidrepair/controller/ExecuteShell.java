package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.excecoes.ErroExecucaoShellException;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.logging.Logger;

public class ExecuteShell {

    private static final Logger log = Logger.getLogger(ExecuteShell.class.getName());

    private FileWriter arquivo;
    private boolean start_writter = true;
    private PrintWriter gravar;

    public String executeCommand(String command, @NotNull boolean write_file, String path_file) {

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

                if (write_file) {
                    fileWritterShell(line, path_file);
                }
            }

            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = stderr.readLine()) != null){

                System.out.println(line);

                if (write_file) {
                    fileWritterShell(line, path_file);
                }
            }

            if (this.arquivo != null){

                this.arquivo.close();
                this.start_writter = true;
            }

            in.close();

        } catch (IOException e) {

            Messages.showMessageDialog("Erro ao tentar executar o comando", "Comando Inválido", Messages.getErrorIcon());
            throw new ErroExecucaoShellException("Erro ao tentar executar o comando");

        }

        return statusline;
    }


    private void fileWritterShell(String line, String path_file) throws IOException {

        if (start_writter){

            File file = new File(path_file);
            FileWriter arquivo = new FileWriter(file.getCanonicalFile());
            this.arquivo = arquivo;
            gravar = new PrintWriter(arquivo);
            this.start_writter = false;
            this.gravar.printf(line + "\n");

        } else {
            this.gravar.printf(line + "\n");
        }
    }
}
