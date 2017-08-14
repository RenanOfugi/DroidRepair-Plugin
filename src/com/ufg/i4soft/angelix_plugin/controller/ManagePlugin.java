package com.ufg.i4soft.angelix_plugin.controller;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.angelix_plugin.model.ProjectData;
import com.ufg.i4soft.angelix_plugin.view.windows.ChooseRepair;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.Optional;

public class ManagePlugin {

    public void start(){

        Optional<String> pathOptional = Optional.ofNullable(collectPath());

        if (pathOptional.isPresent()) {

            ChooseRepair.showTypeRepair();

            ProjectData data = new ProjectData();
            data.setTypeRepair(ChooseRepair.getTyperepair());

            selectRepair(data.getTypeRepair(), pathOptional.get());
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
