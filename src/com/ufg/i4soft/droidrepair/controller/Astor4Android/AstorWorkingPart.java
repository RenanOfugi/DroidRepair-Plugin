package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.ExecuteShell;
import com.ufg.i4soft.droidrepair.controller.ExecuteShellThread;
import com.ufg.i4soft.droidrepair.excecoes.ErroExecucaoShellException;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;

public class AstorWorkingPart {

    public void startAstorWorking() {

        mavenClean();
        executeAVD();
    }

    private void mavenClean() {

        String mvn = "cd " + Astor4AndroidData.getAstorworking_directory() + ";mvn clean compile";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(mvn);
    }

    private void executeAVD() {

        String avd_name = Messages.showInputDialog("Entre com o nome do AVD a ser executado " +
                "(lembrando de te-lo baixado seguindo a documentação na wiki", "AVD NAME", Messages.getInformationIcon());

        String execute_avd_command = "export QEMU_AUDIO_DRV=none;" +
                "cd " + Astor4AndroidData.getAndroid_home() + "/tools;" +
                "sudo -b ./emulator -avd " + avd_name + " -no-skin -no-window -no-boot-anim";

        String password = Messages.showPasswordDialog(null, "É necessário elevação de privilégios: Por favor, digite sua senha", "Permissão Administrador", Messages.getInformationIcon());

        Thread thread = new Thread(new ExecuteShellThread(execute_avd_command, true, password));
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new ErroExecucaoShellException("Erro na execução da Thread");
        }
    }

    private void executeAstorWorking() {


    }
}