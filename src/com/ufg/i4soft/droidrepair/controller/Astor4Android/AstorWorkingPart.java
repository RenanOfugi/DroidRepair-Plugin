package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.ExecuteShell;
import com.ufg.i4soft.droidrepair.controller.ExecuteShellThread;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;

public class AstorWorkingPart {

    public boolean startAstorWorking() {

        mavenClean();

        boolean avd_ok = executeAVD();

        if (avd_ok) {

            executeAstorWorking();
        }

        return avd_ok;
    }

    private void mavenClean() {

        String mvn = "cd " + Astor4AndroidData.getAstorworker_directory() + ";mvn clean compile";

        ExecuteShell shell = new ExecuteShell();
        //shell.executeCommand(mvn, false, null);
        shell.executeShellLoadingMessage(mvn, false, null);
    }

    private boolean executeAVD() {

        String execute_avd_command = "export QEMU_AUDIO_DRV=none;" +
                "cd " + Astor4AndroidData.getAndroid_home() + "/tools;" +
                "sudo -b ./emulator -avd " + Astor4AndroidData.getAvdname() + " -no-skin -no-window -no-boot-anim";

        String password = Messages.showPasswordDialog(null, "É necessário elevação de privilégios: Por favor, digite sua senha", "Permissão Administrador", Messages.getInformationIcon());

        if (password != null) {

            Thread thread = new Thread(new ExecuteShellThread(execute_avd_command, true, password));
            thread.start();

            return true;
        }

        return false;
    }

    private void executeAstorWorking() {

        String build_maven = "cd " + Astor4AndroidData.getAstorworker_directory() + ";" +
                "mvn dependency:build-classpath;" +
                "mvn dependency:build-classpath | egrep -v \"(^\\[INFO\\]|^\\[WARNING\\])\" | tee astorworker-classpath.txt;" +
                "cd " + Astor4AndroidData.getAstorworker_directory() + ";" +
                "echo sdk.dir=" + Astor4AndroidData.getAndroid_home() + " | " +
                "tee local.properties";

        ExecuteShell shell = new ExecuteShell();
        shell.executeShellLoadingMessage(build_maven, false, null);

        String execute_astorworking = "cd " + Astor4AndroidData.getAstorworker_directory() + ";" +
                Astor4AndroidData.getCommandbase_run_astorworking() + " " +
                "-hostip " + Astor4AndroidData.getHostip() + " " +
                "-hostport " + Astor4AndroidData.getHostport() + " " +
                "-workerip " + Astor4AndroidData.getWorkerip() + " " +
                "-workerport " + Astor4AndroidData.getWorkerport() + " " +
                "-platformtools " + Astor4AndroidData.getAndroid_home() + "/platform-tools " +
                "-buildtools " + Astor4AndroidData.getBuildtools() + " " +
                "-androidjar " + Astor4AndroidData.getAndroidjar() + " " +
                "-androidsdk " + Astor4AndroidData.getAndroid_home();

        ExecuteShellThread shellThread = new ExecuteShellThread(execute_astorworking, false, null);
        Thread thread = new Thread(shellThread);
        thread.start();
    }
}