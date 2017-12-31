package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.ExecuteShell;
import com.ufg.i4soft.droidrepair.controller.ExecuteShellThread;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

public class AstorWorkingPart {

    public boolean startAstorWorking() {

        mavenClean();
        executeAVD();

        return executeAstorWorking();
    }

    private void mavenClean() {

        String mvn = "cd " + Astor4AndroidData.getAstorworking_directory() + ";mvn clean compile";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(mvn, false, null);
    }

    private void executeAVD() {

        String avd_name = Messages.showInputDialog("Entre com o nome do AVD a ser executado " +
                "(lembrando de te-lo baixado seguindo a documentação na wiki)", "AVD NAME", Messages.getInformationIcon());

        String execute_avd_command = "export QEMU_AUDIO_DRV=none;" +
                "cd " + Astor4AndroidData.getAndroid_home() + "/tools;" +
                "sudo -b ./emulator -avd " + avd_name + " -no-skin -no-window -no-boot-anim";

        String password = Messages.showPasswordDialog(null, "É necessário elevação de privilégios: Por favor, digite sua senha", "Permissão Administrador", Messages.getInformationIcon());

        Thread thread = new Thread(new ExecuteShellThread(execute_avd_command, true, password));
        thread.start();

    }

    private boolean executeAstorWorking() {

        String build_maven = "cd " + Astor4AndroidData.getAstorworking_directory() + ";" +
                "mvn dependency:build-classpath;" +
                "mvn dependency:build-classpath | egrep -v \"(^\\[INFO\\]|^\\[WARNING\\])\" | tee astorworker-classpath.txt";

        String create_file_local_properties = "cd " + Astor4AndroidData.getAstorworking_directory() + ";" +
                "echo sdk.dir=" + Astor4AndroidData.getAndroid_home() + " | " +
                "tee local.properties";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(build_maven, false, null);
        shell.executeCommand(create_file_local_properties, false, null);

        collectParameters();

        boolean parameters_ok = verifyParameters();

        if (parameters_ok) {

            String execute_astorworking = "cd " + Astor4AndroidData.getAstorworking_directory() + ";" +
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

            return true;

        } else {

            Messages.showMessageDialog("Reparo cancelado (cancelamento intencional ou ausencia de parametros)",
                    "Não Foi Possivel Concluir O Reparo", Messages.getErrorIcon());

            return false;
        }
    }

    private void collectParameters() {

        MainWindows buildtools_windows = new MainWindows();
        String buildtools = buildtools_windows.viewChooseFile("Build-Tools", "Escolha uma pasta do diretório build-tools. ex: /home/user/Documents/Android/Sdk/build-tools/25.0.0");
        Astor4AndroidData.setBuildtools(buildtools);

        MainWindows androidjar_windows = new MainWindows();
        String androidjar = androidjar_windows.viewChooseFile("AndroidJar", "Selecione o android.jar. Ex: /home/user/Documents/Android/Sdk/platforms/android-25/android.jar");
        Astor4AndroidData.setAndroidjar(androidjar);

        String hostip = Messages.showInputDialog("Insira o IP do host. Host da máquina que executa o Astor4Android", "HOSTIP", Messages.getInformationIcon());
        Astor4AndroidData.setHostip(hostip);

        String hostport = Messages.showInputDialog("Insira a porta do host que executa o Astor4Android", "HOSTPORT", Messages.getInformationIcon());
        Astor4AndroidData.setHostport(hostport);

        String workerip = Messages.showInputDialog("Insira o IP da máquina que o AstorWorker irá ser executado", "WORKERIP", Messages.getInformationIcon());
        Astor4AndroidData.setWorkerip(workerip);

        String workerport = Messages.showInputDialog("Insira a porta da máquina que o AstorWorker irá ser executado", "WORKERPORT", Messages.getInformationIcon());
        Astor4AndroidData.setWorkerport(workerport);
    }

    private boolean verifyParameters() {

        return Astor4AndroidData.getBuildtools() != null &&
                Astor4AndroidData.getAndroidjar() != null &&
                Astor4AndroidData.getHostip() != null &&
                Astor4AndroidData.getHostport() != null &&
                Astor4AndroidData.getWorkerip() != null &&
                Astor4AndroidData.getWorkerport() != null;
    }
}