package com.ufg.i4soft.droidrepair.view.windows;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;

public class MainWindows {

    public String viewChooseFile(String title, String description) {

        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
        descriptor.setTitle(title);
        descriptor.setDescription(description);

        VirtualFile file = FileChooser.chooseFile(descriptor, ProjectData.getProject(), null);

        FilterData filterData = new FilterData();

        return filterData.filterPath(file);
    }

    public static String viewInput() {

        return Messages.showInputDialog(ProjectData.getProject(), "Insira os parâmetros para o Angelix", "Inserção De Parâmetros", Messages.getInformationIcon());
    }
}
