package com.ufg.i4soft.angelix_plugin.controller;

import com.ufg.i4soft.angelix_plugin.excecoes.ArquivoNaoAbre;
import com.ufg.i4soft.angelix_plugin.model.FilterData;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.io.File;
import java.io.IOException;

public class DiffAndroidStudio {

    public void executeDiff(String arquivo_antes, String arquivo_depois){

        ExecuteShell shell = new ExecuteShell();
        String caminho_androidStudio = getPathAndroidStudio();

        if (caminho_androidStudio != null){

            String command = "./" + caminho_androidStudio + " diff " + arquivo_antes + " " + arquivo_depois;

            shell.executeCommand(command);
        }
    }

    private String getPathAndroidStudio(){

        String path = MainWindows.viewChooseFile("Diretorio Android Studio", "Selecione a pasta bin do Android Studio");

        String[] fileValid = {"studio.sh"};

        FilterData filterData = new FilterData();

        boolean valid = filterData.verifyExtensionFile(path, fileValid);

        if (valid){
            return path;
        } else {
            return "";
        }
    }

    public void openDiffFile(String path){

        try {

            java.awt.Desktop.getDesktop().open(new File(path));

        } catch (IOException e){

            throw new ArquivoNaoAbre("Não foi possivel abrir arquivo com diff de reparo");
        }
    }
}
