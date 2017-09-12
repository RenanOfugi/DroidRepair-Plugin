package com.ufg.i4soft.droidrepair.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecuteShellTest {

    @Test
    public void executeCommand() throws Exception {

        String command = "gksudo apt-get update";

        ExecuteShell shell = new ExecuteShell();
        String teste = shell.executeCommand(command);
        System.out.println("last line: " + teste);
    }

}