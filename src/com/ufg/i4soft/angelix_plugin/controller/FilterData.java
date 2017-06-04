package com.ufg.i4soft.angelix_plugin.controller;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

public class FilterData {

    public String filterPath(VirtualFile file, Project project) {

        String path;

        if (file != null && !file.getPath().endsWith("\\")) {

            path = file.getPath();
            return path;

        } else if (file != null && file.getPath().endsWith("\\")) {
            Messages.showMessageDialog(project, "O diretório escolhido não é um arquivo", "Parâmetro Inválido", Messages.getWarningIcon());
            return null;
        } else {
            Messages.showMessageDialog(project, "Não foi identificado nenhum diretório válido", "Parâmetro Inválido", Messages.getWarningIcon());
            return null;
        }
    }

    public String[] splitPath(String path) {

        int local_split = path.lastIndexOf("\\");

        String[] subPath = new String[2];
        subPath[0] = path.substring(0, local_split + 1);
        subPath[1] = path.substring(local_split, path.length());

        return subPath;
    }
}
