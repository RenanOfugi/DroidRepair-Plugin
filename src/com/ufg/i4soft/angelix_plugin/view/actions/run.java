package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.angelix_plugin.controller.ManageAngelix;
import com.ufg.i4soft.angelix_plugin.model.ProjectData;
import com.ufg.i4soft.angelix_plugin.view.windows.ChooseRepair;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.Optional;

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

        Optional<String> pathOptional = Optional.ofNullable(collectPath());

        if (pathOptional.isPresent()) {

            ChooseRepair.showTypeRepair();

            ProjectData data = new ProjectData();
            data.setTypeRepair(ChooseRepair.getTyperepair());

            selectRepair(data.getTypeRepair(), pathOptional.get());
        }
    }

    private void setProject() {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        ProjectData.setProject(project);
    }

    private void selectRepair(String repair, String path) {

        switch (repair) {

            case "angelix":
                ManageAngelix angelix = new ManageAngelix();
                angelix.runAngelix(path);
                break;

            case "genprog":
                break;

            default:
                Messages.showMessageDialog("escolha de ferramenta inválida", "Ferramenta De Reparo Inválido", Messages.getWarningIcon());
        }
    }

    private String collectPath() {

        return MainWindows.viewChooseFile();
    }

}
