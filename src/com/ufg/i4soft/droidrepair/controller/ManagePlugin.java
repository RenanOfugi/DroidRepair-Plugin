package com.ufg.i4soft.droidrepair.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.Angelix.ManageAngelix;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.ChooseRepair;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ManagePlugin {

    public void start() {

        ChooseRepair.showTypeRepair();

        ProjectData data = new ProjectData();
        data.setTypeRepair(ChooseRepair.getTyperepair());

        selectRepair(data.getTypeRepair());
    }

    private void selectRepair(String repair) {

        switch (repair) {

            case "angelix":
                ManageAngelix angelix = new ManageAngelix();
                angelix.startRepairTool();
                break;

            case "genprog":
                Messages.showMessageDialog(ProjectData.getProject(), "Funcionalidade para " + repair + " não implementada :(", "Não É Possivel Executar", Messages.getWarningIcon());
                break;

            default:
                Messages.showMessageDialog("escolha de ferramenta inválida", "Ferramenta De Reparo Inválido", Messages.getWarningIcon());
        }
    }
}
