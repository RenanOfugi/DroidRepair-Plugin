package com.ufg.i4soft.angelix_plugin.controller;

import com.ufg.i4soft.angelix_plugin.contract.RepairTool;
import com.ufg.i4soft.angelix_plugin.model.FilterData;
import com.ufg.i4soft.angelix_plugin.model.ProjectData;
import com.ufg.i4soft.angelix_plugin.view.windows.MainWindows;

import java.util.ArrayList;

class ManageAngelix implements RepairTool{

    public void startRepairTool(String path){

        ArrayList<String> args = collectParameters(path);

        verifyInputs(args);
    }

    public ArrayList<String> collectParameters(String path){

        ArrayList<String> parameters = new ArrayList<>();
        String[] subPathes;

        FilterData filterData = new FilterData();

        subPathes = filterData.splitPath(path);
        //boolean file_valid = false;

        /*if (path != null) {

            FilterData filterData = new FilterData();
            subPathes = filterData.splitPath(path);

            String[] extensionValid = {".java", ".c"};

            file_valid = filterData.verifyExtensionFile(subPathes[1], extensionValid);

        }*/

        if (/*file_valid*/path != null){

            setPathofPatch(subPathes[0]); //Caminho onde sera gerado o patch

            parameters.add(subPathes[0]); // Diretório: sem o arquivo
            parameters.add(subPathes[1]); // Arquivo a ser executado

            String outros_paramentros = MainWindows.viewInput();
            parameters.add(outros_paramentros);

        } else {
            parameters.add(null);
        }

        return parameters;
    }

    public void verifyInputs(ArrayList<String> args) {

        boolean parametrosNaoVazios = true;

        for (String arg : args) {
            if (arg == null) {
                parametrosNaoVazios = false;
            }
        }

        if (parametrosNaoVazios) {
            executeRepairTool(args);
        }
    }

    public void executeRepairTool(ArrayList<String> args) {

        ExecuteShell shell = new ExecuteShell();

        String command = "angelix " + args.get(0) + " " + args.get(1) + " " + args.get(2);

        String lastline = shell.executeCommand(command);

        if (lastline.equals("SUCCESS")){

            DiffAndroidStudio diff = new DiffAndroidStudio();
            diff.searchDiffFile();
        }

    }

    private void setPathofPatch(String path){

        ProjectData.setPath_of_patch(path);
    }

}
