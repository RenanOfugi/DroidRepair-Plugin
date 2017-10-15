package com.ufg.i4soft.droidrepair.controller;

import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoAbre;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.io.File;
import java.io.IOException;

public class DiffAndroidStudio {

    public void showDiff(){

        String path_androidstudio = getAndroidStudioExecutable();

        String command = path_androidstudio + " " +
                ProjectData.getPath_of_patch() + "/" + ProjectData.getName_filepatch() + " " +
                ProjectData.getPath_of_patch() + "/" + "patchPuro.txt";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(command);
    }

    private static String getAndroidStudioExecutable(){

        MainWindows mainWindows = new MainWindows();

        return mainWindows.viewChooseFile("Selecione Executavel","Selecionar o idea.sh ou studio.sh");
    }
}
