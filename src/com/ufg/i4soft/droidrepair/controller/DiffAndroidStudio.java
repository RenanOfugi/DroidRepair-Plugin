package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoAbre;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.io.File;
import java.io.IOException;

public class DiffAndroidStudio {

    public void showDiff(){

        String path_androidstudio = getAndroidStudioExecutable();

        String command = path_androidstudio + " " + "diff" + " " +
                ProjectData.getPath_of_patch() + "/" + ProjectData.getName_filepatch() + " " +
                ProjectData.getPath_of_patch() + "/" + "patchPuro.txt";

        if (path_androidstudio.toLowerCase().endsWith("idea.sh") || path_androidstudio.toLowerCase().endsWith("studio.sh")){

            ExecuteShell shell = new ExecuteShell();
            shell.executeCommand(command, false, null);
        } else {
            Messages.showMessageDialog("Selecione o arquivo idea.sh ou studio.sh","Arquivo Incorreto", Messages.getErrorIcon());
        }

    }

    private static String getAndroidStudioExecutable(){

        MainWindows mainWindows = new MainWindows();

        return mainWindows.viewChooseFile("Selecione Executavel","Selecionar o idea.sh ou studio.sh");
    }
}
