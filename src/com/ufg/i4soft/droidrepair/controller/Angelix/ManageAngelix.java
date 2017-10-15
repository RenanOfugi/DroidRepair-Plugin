package com.ufg.i4soft.droidrepair.controller.Angelix;

import com.ufg.i4soft.droidrepair.contract.RepairTool;
import com.ufg.i4soft.droidrepair.controller.DiffAndroidStudio;
import com.ufg.i4soft.droidrepair.controller.*;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.util.ArrayList;

public class ManageAngelix implements RepairTool{

    public void startRepairTool(String path){

        ArrayList<String> args = collectParameters(path);

        verifyInputs(args);
    }

    public ArrayList<String> collectParameters(String path){

        ArrayList<String> parameters = new ArrayList<>();
        String[] subPathes;

        FilterData filterData = new FilterData();

        subPathes = filterData.splitPath(path);

        if (path != null){

            setPathofPatch(subPathes[0]); //Caminho onde sera gerado o patch, sem o ultimo diretorio

            parameters.add(subPathes[0]); // Diretório: sem o arquivo
            parameters.add(subPathes[1]); // Arquivo a ser executado

            String outros_paramentros = MainWindows.viewInput();
            parameters.add(outros_paramentros); //restante dos parametros para serem executados

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

        String command = "cd ~/angelix;" +
                ". activate;" +
                "cd " + ProjectData.getPath_of_patch() + ";" + //Mudar para diretório onde será gerado o patch
                "angelix " + args.get(0) + " " + args.get(1) + " " + args.get(2);

        FileManipulation fileManipulation = new FileManipulation();
        boolean deleted_allfiles = fileManipulation.deleteAllPatches(ProjectData.getPath_of_patch()); //deleta todos os arquivos .patch do diretório escolhido

        String statusline = shell.executeCommand(command);

        if (statusline.equals("sucess") && deleted_allfiles){

            fileManipulation.searchFilePatch(ProjectData.getPath_of_patch()); //busca o arquivo .patch gerado
            fileManipulation.deleteOldLines(ProjectData.getPath_of_patch(), ProjectData.getName_filepatch(),ProjectData.getPath_of_patch(),"patchPuro.txt"); // gera um novo arquivo com o patch sem nenhuma linha de código antigo modificado

            DiffAndroidStudio diff = new DiffAndroidStudio();
            diff.showDiff();
        }

    }

    private void setPathofPatch(String path){

        FilterData filterData = new FilterData();
        String[] path_patch = filterData.splitPath(path);
        ProjectData.setPath_of_patch(path_patch[0]);
    }

}
