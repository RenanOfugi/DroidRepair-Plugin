package com.ufg.i4soft.angelix_plugin.enums;

public enum FileExtensions {

    C(".c"), JAVA(".java"), STUDIO("studio.sh");

    private String value;

    FileExtensions(String value) {

        this.value = value;
    }

    public String getValue(){

        return this.value;
    }
}
