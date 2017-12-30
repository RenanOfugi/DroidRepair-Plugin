package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.ufg.i4soft.droidrepair.contract.RepairTool;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.util.ArrayList;

public class ManageAstor4Android implements RepairTool {

    @Override
    public void startRepairTool() {

        collectParameters();
        executeRepairTool();
    }

    @Override
    public ArrayList<String> collectParameters(String path) {

        return null;
    }

    @Override
    public void collectParameters() {

        MainWindows android_windows = new MainWindows();
        String android_home = android_windows.viewChooseFile("ANDROID_HOME", "Selecione o diretório onde está a pasta Android/sdk");
        Astor4AndroidData.setAndroid_home(android_home);

        MainWindows java_windows = new MainWindows();
        String java_home = java_windows.viewChooseFile("JAVA_HOME", "Selecione o diretório onde está o JDK");
        Astor4AndroidData.setJava_home(java_home);

        MainWindows astorworking_windows = new MainWindows();
        String astorworking_directory = astorworking_windows.viewChooseFile("ASTORWORKING", "Selecione o repositório do AstorWorking");
        Astor4AndroidData.setAstorworking_directory(astorworking_directory);

        MainWindows astor4android_windows = new MainWindows();
        String astor4android_directory = astor4android_windows.viewChooseFile("ASTOR4ANDROID", "Selecione o repositório do Astor4Android");
        Astor4AndroidData.setAstor4android_directory(astor4android_directory);
    }

    @Override
    public boolean verifyInputs(ArrayList<String> args) {
        return false;
    }

    @Override
    public void executeRepairTool(ArrayList<String> args) {

    }

    @Override
    public void executeRepairTool() {

        AstorWorkingPart astorWorking = new AstorWorkingPart();
        astorWorking.startAstorWorking();

        Astor4AndroidPart android4android = new Astor4AndroidPart();
        android4android.startAstor4Android();
    }
}
