package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

public class runAngelix extends AnAction {

    public runAngelix(){
        super("Plugin Angelix");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        testePlugin(event);
    }

    private void testePlugin(AnActionEvent event){

        Project project = event.getData(PlatformDataKeys.PROJECT);
        MainWindows.viewChooseFile(project);
    }
}
