package com.ufg.i4soft.angelix_plugin.controller;

import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.ArrayList;

public class ManageAngelix {

    public void runAngelix(String path){

        ArrayList<String> args = collectParameters(path);

        verifyInputs(args);
    }

    private ArrayList<String> collectParameters(String path){

        ArrayList<String> parameters = new ArrayList<>();

        if (path != null) {

            FilterData filterData = new FilterData();
            String[] subPathes = filterData.splitPath(path);
            parameters.add(subPathes[0]);
            parameters.add(subPathes[1]);

            String outros_paramentros = MainWindows.viewInput();
            parameters.add(outros_paramentros);

        } else {
            parameters.add("");
        }

        return parameters;
    }

    private void verifyInputs(ArrayList<String> args) {

        boolean parametrosNaoVazios = true;

        for (String arg : args) {
            if (arg == null) {
                parametrosNaoVazios = false;
            }
        }

        if (parametrosNaoVazios) {
            executeAngelix(args);
        }
    }

    private void executeAngelix(ArrayList<String> args) {

        ExecuteShell shell = new ExecuteShell();

        String command = "angelix " + args.get(0) + " " + args.get(1) + " " + args.get(2);

        shell.executeCommand(command);

        //System.out.println(output);
    }

}
