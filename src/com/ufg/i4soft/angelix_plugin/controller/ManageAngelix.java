package com.ufg.i4soft.angelix_plugin.controller;

import com.ufg.i4soft.angelix_plugin.model.FilterData;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.ArrayList;

class ManageAngelix {

    void runAngelix(String path){

        ArrayList<String> args = collectParameters(path);

        verifyInputs(args);
    }

    private ArrayList<String> collectParameters(String path){

        ArrayList<String> parameters = new ArrayList<>();
        String[] subPathes = new String[3];
        boolean file_valid = false;

        if (path != null) {

            FilterData filterData = new FilterData();
            subPathes = filterData.splitPath(path);

            String[] extensionValid = {".java", ".c"};

            file_valid = filterData.verifyExtensionFile(subPathes[1], extensionValid);

        }

        if (file_valid){

            parameters.add(subPathes[0]); // Diretório: sem o arquivo
            parameters.add(subPathes[1]); // Arquivo a ser executado

            String outros_paramentros = MainWindows.viewInput();
            parameters.add(outros_paramentros);

        } else {
            parameters.add(null);
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

        String lastline = shell.executeCommand(command);

        if (lastline.equals("SUCCESS")){

            DiffAndroidStudio diff = new DiffAndroidStudio();
            diff.openDiffFile(""); //TODO: pensar em como pegar o path do patch
        }

    }

}
