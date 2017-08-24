package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.ufg.i4soft.angelix_plugin.controller.ManagePlugin;
import com.ufg.i4soft.angelix_plugin.model.ProjectData;

public class run extends AnAction {

    private static AnActionEvent event;

    public run() {
        super("Plugin Repair");
    }

    public static AnActionEvent getEvent() {
        return event;
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        run.event = event;
        runPlugin();
    }

    private void runPlugin() {

        setProject();

        ManagePlugin plugin = new ManagePlugin();
        plugin.start();
    }

    private void setProject() {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        ProjectData.setProject(project);
    }
}
