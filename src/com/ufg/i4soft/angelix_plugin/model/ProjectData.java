package com.ufg.i4soft.angelix_plugin.model;

import com.intellij.openapi.project.Project;

public class ProjectData {

    private static Project project;
    private String typeRepair;
    private String path_file_test;

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

    public String getPath_file_test() {
        return path_file_test;
    }

    public void setPath_file_test(String path_file_test) {
        this.path_file_test = path_file_test;
    }
}
