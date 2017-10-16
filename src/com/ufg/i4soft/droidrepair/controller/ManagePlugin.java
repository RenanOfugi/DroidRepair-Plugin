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

    public void start(){

        Optional<String> pathOptional = Optional.ofNullable(collectPath());

        boolean file_valid;

        FilterData filterData = new FilterData();

        try {

            file_valid = filterData.verifyExtensionFile(pathOptional.get());

            if (file_valid) {

                ChooseRepair.showTypeRepair();

                ProjectData data = new ProjectData();
                data.setTypeRepair(ChooseRepair.getTyperepair());

                selectRepair(data.getTypeRepair(), pathOptional.get());
            }

        } catch (NoSuchElementException e){

            Messages.showMessageDialog("Arquivo para reparo ausente", "Arquivo Ausente", Messages.getInformationIcon());
        }
    }

    private String collectPath() {

        MainWindows windows = new MainWindows();
        return windows.viewChooseFile("Selecione O Diretório Do Arquivo", "Selecione o arquivo que deseja submeter à execução do angelix");
    }

    private void selectRepair(String repair, String path) {

        switch (repair) {

            case "angelix":
                ManageAngelix angelix = new ManageAngelix();
                angelix.startRepairTool(path);
                break;

            case "genprog":
                Messages.showMessageDialog(ProjectData.getProject(), "Funcionalidade para " + repair + " não implementada :(", "Não É Possivel Executar", Messages.getWarningIcon());
                break;

            default:
                Messages.showMessageDialog("escolha de ferramenta inválida", "Ferramenta De Reparo Inválido", Messages.getWarningIcon());
        }
    }
}
