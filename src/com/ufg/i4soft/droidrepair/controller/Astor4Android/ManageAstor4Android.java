package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.contract.RepairTool;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.util.ArrayList;

public class ManageAstor4Android implements RepairTool {

    @Override
    public void startRepairTool() {

        collectParameters();

        boolean parameters_ok = verifyInputs();

        if (parameters_ok) {

            executeRepairTool();
        }

        finishExecution();
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
        String astorworking_directory = astorworking_windows.viewChooseFile("ASTORWORKER", "Selecione o repositório do AstorWorker");
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
    public boolean verifyInputs() {

        return Astor4AndroidData.getAndroid_home() != null &&
                Astor4AndroidData.getJava_home() != null &&
                Astor4AndroidData.getAstorworking_directory() != null &&
                Astor4AndroidData.getAstor4android_directory() != null;
    }

    @Override
    public void executeRepairTool(ArrayList<String> args) {

    }

    @Override
    public void executeRepairTool() {

        AstorWorkingPart astorWorkingpart = new AstorWorkingPart();
        boolean astorworker_ok = astorWorkingpart.startAstorWorking();

        if (astorworker_ok) {
            Astor4AndroidPart android4androidpart = new Astor4AndroidPart();
            android4androidpart.startAstor4Android();
        }
    }

    private void finishExecution() {

        Messages.showMessageDialog("Tentativa de Reparo Finalizado!" +
                " Para encerrar o AVD, copie e cole no terminal:" +
                " 'sudo printf 'auth %s\\nkill\\n' $(sudo cat ~/.emulator_console_auth_token) | netcat localhost 5554'", "Concluido", Messages.getInformationIcon());
    }
}
