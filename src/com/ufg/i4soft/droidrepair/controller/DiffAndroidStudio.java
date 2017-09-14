package com.ufg.i4soft.droidrepair.controller;

import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoAbre;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

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

        MainWindows windows = new MainWindows();
        String path = windows.viewChooseFile("Diretorio Android Studio", "Selecione a pasta bin do Android Studio");

        //String[] fileValid = {"studio.sh"};

        FilterData filterData = new FilterData();

        boolean valid = filterData.verifyExtensionFile(path);

        if (valid){
            return path;
        } else {
            return "";
        }
    }

    public void searchDiffFile(){

        File baseFolder = new File(ProjectData.getPath_of_patch());

        File[] files = baseFolder.listFiles();

        File file_lastPatch = null;

        if (files != null){

            file_lastPatch = searchLastFileModified(files);
        }

        if (file_lastPatch != null){

            openDiffFile(file_lastPatch.getPath());
        }
    }

    private File searchLastFileModified(File[] files){

        Long lastFile_date = 0L;
        Long currentFile_date = 0L;
        File lastPatch = null;

        for (File file: files) {

            if (file.getPath().endsWith(".patch")){

                currentFile_date = file.lastModified();
            }

            if(currentFile_date > lastFile_date){

                lastFile_date = currentFile_date;
                lastPatch = file;
            }
        }

        return lastPatch;
    }

    private void openDiffFile(String path){

        try {

            java.awt.Desktop.getDesktop().open(new File(path));

        } catch (IOException e){

            throw new ArquivoNaoAbre("Não foi possivel abrir arquivo com diff de reparo");
        }
    }
}
