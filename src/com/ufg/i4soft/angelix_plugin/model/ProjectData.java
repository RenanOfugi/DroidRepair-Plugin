package com.ufg.i4soft.angelix_plugin.model;

import com.intellij.openapi.project.Project;

public class ProjectDate {

    private static Project project;
    private static String typeRepair;

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        ProjectDate.project = project;
    }

    public static String getTypeRepair() {
        return typeRepair;
    }

    public static void setTypeRepair(String typeRepair) {
        ProjectDate.typeRepair = typeRepair;
    }
}
