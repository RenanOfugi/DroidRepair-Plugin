package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.ufg.i4soft.angelix_plugin.controller.ManageAngelix;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.ArrayList;

public class runAngelix extends AnAction {

    public runAngelix(){
        super("Plugin Angelix");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        runPlugin(event);
    }

    private void runPlugin(AnActionEvent event){

        Project project = event.getData(PlatformDataKeys.PROJECT);

        ArrayList<String> parameters = collectParameter(project);

        ManageAngelix angelix = new ManageAngelix();
        angelix.verifyInputs(parameters);
    }

    private ArrayList<String> collectParameter(Project project){

        ArrayList<String> parameters = new ArrayList<>();

        String diretorio = MainWindows.viewChooseFile(project);
        parameters.add(diretorio);

        String outros_paramentros = MainWindows.viewInput(project);
        parameters.add(outros_paramentros);

        return parameters;
    }

}
