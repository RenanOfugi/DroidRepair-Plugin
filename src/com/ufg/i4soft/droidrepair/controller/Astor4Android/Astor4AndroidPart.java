package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.intellij.openapi.ui.Messages;
import com.ufg.i4soft.droidrepair.controller.ExecuteShell;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

public class Astor4AndroidPart {

    public void startAstor4Android() {

        mavenClean();

        collectParameters();

        boolean parameters_ok = verifyParameters();

        if (parameters_ok) {

            executeAstor4Android();
        }
    }

    private void mavenClean() {

        String mvn = "cd " + Astor4AndroidData.getAstor4android_directory() + ";mvn clean compile";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(mvn, false, null);
    }

    private void collectParameters() {

        MainWindows location_windows = new MainWindows();
        String location_directory = location_windows.viewChooseFile("LOCATION", "Selecione o diretório do projeto que sofrerá o reparo");
        Astor4AndroidData.setLocation(location_directory);

        if (Astor4AndroidData.getLocation() != null) {

            String mode = Messages.showInputDialog("entre com o tipo de reparo escolhido (statement, statement-remove, mutation or custom", "MODE", Messages.getInformationIcon());
            Astor4AndroidData.setMode(mode);
        }

        if (Astor4AndroidData.getMode() != null) {

            String javacompliancelevel = Messages.showInputDialog("Entre com o nível de conformidade do codigo fonte. 8 é o nivel recomendado", "JAVA COMPLIANCE LEVEL", Messages.getInformationIcon());
            Astor4AndroidData.setJava_compliance_level(javacompliancelevel);
        }

        if (Astor4AndroidData.getJava_compliance_level() != null) {

            String stopfirst = Messages.showInputDialog("A execução deverá parar depois do primeiro reparo? (true ou false)", "STOP FIRST", Messages.getInformationIcon());
            Astor4AndroidData.setStopfirst(stopfirst);
        }

        if (Astor4AndroidData.getStopfirst() != null) {

            String flthreshold = Messages.showInputDialog("Entre com o valor minimo para fault localization (entre 0 e 1)", "FAULT LOCALIZATION VALUE", Messages.getInformationIcon());
            Astor4AndroidData.setFlthreshold(flthreshold);
        }

        if (Astor4AndroidData.getFlthreshold() != null) {

            String instrumentationfailing = Messages.showInputDialog("Entre com testes de instrumentação separados por dois-pontos", "INSTRUMENTATION FAILING", Messages.getInformationIcon());
            Astor4AndroidData.setInstrumentationfailing(instrumentationfailing);
        }
    }

    private boolean verifyParameters() {

        boolean mode_valid;

        if (Astor4AndroidData.getMode().equals("statement") ||
                Astor4AndroidData.getMode().equals("statement-remove") ||
                Astor4AndroidData.getMode().equals("mutation") ||
                Astor4AndroidData.getMode().equals("custom")) {

            mode_valid = true;
        } else {
            Messages.showMessageDialog("Modo de Reparo inválido", "Modo Inválido", Messages.getErrorIcon());
            mode_valid = false;
        }

        boolean stopfirst_valid;

        if (Astor4AndroidData.getStopfirst().equals("true") || Astor4AndroidData.getStopfirst().equals("false")) {
            stopfirst_valid = true;
        } else {
            Messages.showMessageDialog("StopFirst deverá ter como resposta 'true' ou 'false'", "Stopfirst Inválido", Messages.getErrorIcon());
            stopfirst_valid = false;
        }

        boolean flthreshold_valido;
        double flthreshold_double = Double.valueOf(Astor4AndroidData.getFlthreshold());

        if (flthreshold_double >= 0 || flthreshold_double < 1) {
            flthreshold_valido = true;
        } else {
            Messages.showMessageDialog("valor flthreshold inválido", "Flthreshold Inválido", Messages.getErrorIcon());
            flthreshold_valido = false;
        }

        return Astor4AndroidData.getLocation() != null &&
                mode_valid &&
                Astor4AndroidData.getJava_compliance_level() != null &&
                stopfirst_valid &&
                flthreshold_valido &&
                Astor4AndroidData.getInstrumentationfailing() != null;
    }

    private void executeAstor4Android() {

        Messages.showMessageDialog("Será realizado a tentativa de reparo. Este procedimento poderá demorar algum tempo. Seja paciente", "INICIO DE REPARO", Messages.getWarningIcon());

        MainWindows windows = new MainWindows();
        String path = windows.viewChooseFile("LOG REPARO", "Selecione o local onde será gerado o log de reparo");

        String build_maven = "cd " + Astor4AndroidData.getAstor4android_directory() + ";" +
                "mvn dependency:build-classpath;" +
                "mvn dependency:build-classpath | egrep -v \"(^\\[INFO\\]|^\\[WARNING\\])\" | tee astor-classpath.txt";

        ExecuteShell shell = new ExecuteShell();
        shell.executeCommand(build_maven, false, null);

        String execute_astor4android = "cd " + Astor4AndroidData.getAstor4android_directory() + ";" +
                Astor4AndroidData.getCommandbase_run_astor4android() + " " +
                "-mode " + Astor4AndroidData.getMode() + " " +
                "-location " + Astor4AndroidData.getLocation() + " " +
                "-androidsdk " + Astor4AndroidData.getAndroid_home() + " " +
                "-androidjar " + Astor4AndroidData.getAndroidjar() + " " +
                "-jvm4testexecution " + Astor4AndroidData.getJava_home() + "/bin " +
                "-javacompliancelevel " + Astor4AndroidData.getJava_compliance_level() + " " +
                "-stopfirst " + Astor4AndroidData.getStopfirst() + " " +
                "-flthreshold " + Astor4AndroidData.getFlthreshold() + " " +
                "-instrumentationfailing " + Astor4AndroidData.getInstrumentationfailing() + " " +
                "-port " + Astor4AndroidData.getHostport();

        if (path != null) {

            shell.executeCommand(execute_astor4android, true, path + "/logAstor4Android.txt");
        }
    }
}
