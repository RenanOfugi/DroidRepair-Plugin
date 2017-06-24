package com.ufg.i4soft.angelix_plugin.controller;

import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.ufg.i4soft.angelix_plugin.model.ProjectData;

public class FilterData {

    public String filterPath(VirtualFile file) {

        String path;

        if (file != null && !file.getPath().endsWith("/")) {

            path = file.getPath();
            return path;

        } else {
            Messages.showMessageDialog(ProjectData.getProject(), "Não foi identificado nenhum diretório válido", "Diretório Inválido", Messages.getWarningIcon());
            return null;
        }
    }

    public String[] splitPath(String path) {

        int local_split = path.lastIndexOf("/");

        String[] subPath = new String[2];
        subPath[0] = path.substring(0, local_split);
        subPath[1] = path.substring(local_split + 1, path.length());

        return subPath;
    }
}
