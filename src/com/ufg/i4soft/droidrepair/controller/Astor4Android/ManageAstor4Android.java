package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.contract.RepairTool;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;

import java.io.File;
import java.util.ArrayList;

public class ManageAstor4Android implements RepairTool {

    @Override
    public void startRepairTool() {

        if (createFolderAstor4Android()) {

            configureAstor4android();

            Messages.showMessageDialog("Bem vindo à execução do Astor4Android! Como é sua primeira vez," +
                            " deverá clonar os repositórios do astorworker e do astor4android dentro da pasta 'droidrepair' na sua home",
                    "REPOSITORIOS NECESSARIOS", Messages.getInformationIcon());

        }

        if (verifyRepositories() && !createFolderAstor4Android()) {

            boolean parameters_ok = verifyInputs();

            if (parameters_ok) {

                executeRepairTool();
                finishExecution();

                Messages.showMessageDialog("Para encerrar o AVD, copie e cole no terminal:" +
                        " 'sudo printf 'auth %s\\nkill\\n' $(sudo cat ~/.emulator_console_auth_token) | netcat localhost 5554'", "AVD SHUTDOWN", Messages.getInformationIcon());
            }
        }

    }

    @Override
    public ArrayList<String> collectParameters(String path) {

        return null;
    }

    @Override
    public void collectParameters() {

        ConfigurationAstor4Android configurationAstor4Android = new ConfigurationAstor4Android();
        configurationAstor4Android.setVariablesWithFileProperties();
    }

    @Override
    public boolean verifyInputs(ArrayList<String> args) {
        return false;
    }

    @Override
    public boolean verifyInputs() {

        return Astor4AndroidData.getJava_home() != null &&
                Astor4AndroidData.getBuildtools() != null &&
                Astor4AndroidData.getAndroidjar() != null;
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

    private void configureAstor4android() {

        ConfigurationAstor4Android configurationAstor4Android = new ConfigurationAstor4Android();

        File file = new File(System.getProperty("user.home") + "/droidrepair/config_astor4android.properties");

        if (file.exists()) {

            collectParameters();
        } else {

            configurationAstor4Android.createConfigurationFile();

            Messages.showMessageDialog("Foi criado um arquivo 'config_astor4android.properties' na pasta droidrepair, verifique se todos as informações" +
                            " contidas nesse arquivo estão corretas, e execute o plugin novamente",
                    "VERIFICAÇÂO DE DADOS", Messages.getInformationIcon());
        }
    }

    private void finishExecution() {

        Messages.showMessageDialog("Tentativa de Reparo Finalizado!", "Concluido", Messages.getInformationIcon());
    }

    private boolean createFolderAstor4Android() {

        File directory = new File(System.getProperty("user.home") + "/droidrepair");

        if (!directory.exists()) {
            directory.mkdir();
            return true;
        }

        return false;
    }

    private boolean verifyRepositories() {

        File astorworker_directory = new File(System.getProperty("user.home") + "/droidrepair/astorworker");
        File astor4android_directory = new File(System.getProperty("user.home") + "/droidrepair/astor4android");

        if (astor4android_directory.exists() && astorworker_directory.exists()) {
            return true;
        } else {
            Messages.showMessageDialog("Os repositórios não estão presentes na pasta 'droidrepair'", "AUSENCIA DE REPOSITORIOS", Messages.getErrorIcon());
            return false;
        }
    }
}
