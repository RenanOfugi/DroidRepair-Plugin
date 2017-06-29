package com.ufg.i4soft.angelix_plugin.model;

import com.intellij.openapi.project.Project;

public class ProjectData {

    private static Project project;
    private String typeRepair;

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        ProjectData.project = project;
    }

    public String getTypeRepair() {
        return typeRepair;
    }

    public void setTypeRepair(String typeRepair) {
        this.typeRepair = typeRepair;
    }
}
