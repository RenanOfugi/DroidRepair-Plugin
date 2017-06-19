package com.ufg.i4soft.angelix_plugin.view.windows;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.ufg.i4soft.angelix_plugin.controller.FilterData;

public class MainWindows {

    public static void windowsCheckboxes() {

        //TODO: Esta classe deverá implementar a interface de usuário que permitirá a escolha do tipo de ferramenta de reparo automatizado de código
    }

    public static String viewChooseFile(Project project) {

        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
        descriptor.setTitle("Selecione O Diretório Do Arquivo");
        descriptor.setDescription("Selecione o arquivo que deseja submeter à execução do angelix");

        VirtualFile file = FileChooser.chooseFile(descriptor, project, null);

        FilterData filterData = new FilterData();

        return filterData.filterPath(file,project);
    }

    public static String viewInput(Project project) {

        String args = Messages.showInputDialog(project, "Insira os parâmetros para o Angelix", "Inserção De Parâmetros", Messages.getInformationIcon());

        if (args == null) {
            Messages.showMessageDialog(project, "Não foi identificado nenhuma entrada de parâmetros", "Parâmetro Inválido", Messages.getWarningIcon());
        }

        return args;
    }
}
