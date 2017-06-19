package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.ufg.i4soft.angelix_plugin.controller.FilterData;
import com.ufg.i4soft.angelix_plugin.controller.ManageAngelix;
import com.ufg.i4soft.angelix_plugin.view.windows.ChooseRepair;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.ArrayList;

public class runAngelix extends AnAction {

    public runAngelix() {
        super("Plugin Repair");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        //runPlugin(event);
        ChooseRepair.main(null);
    }

    private void runPlugin(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);

        ArrayList<String> parameters = collectParameter(project);

        ManageAngelix angelix = new ManageAngelix();
        angelix.runAngelix(parameters);
    }

    private ArrayList<String> collectParameter(Project project) {

        ArrayList<String> parameters = new ArrayList<>();

        String path = MainWindows.viewChooseFile(project);

        if (path != null) {

            FilterData filterData = new FilterData();
            String[] subPathes = filterData.splitPath(path);
            parameters.add(subPathes[0]);
            parameters.add(subPathes[1]);

            String outros_paramentros = MainWindows.viewInput(project);
            parameters.add(outros_paramentros);

        } else {
            parameters.add(null);
        }

        return parameters;
    }

}
