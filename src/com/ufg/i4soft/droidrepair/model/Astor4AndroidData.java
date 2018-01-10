package com.ufg.i4soft.droidrepair.model;

public class Astor4AndroidData {

    // <---------------------------- Parâmetros gerais de execução do Astor4Android --------------------------------------->

    private static String android_home;
    private static String java_home;
    private static String astorworker_directory;
    private static String astor4android_directory;
    private final static String commandbase_run_astorworking = "java -cp $(cat astorworker-classpath.txt):target/classes br.ufg.inf.astorworker.AstorWorker";
    private final static String commandbase_run_astor4android = "java -cp $(cat astor-classpath.txt):target/classes br.ufg.inf.main.evolution.Astor4AndroidMain";

    // <----------------------------- Parâmetros de execução específicas do AstorWorking ----------------------------------->

    private static String hostip;
    private static String hostport;
    private static String workerip;
    private static String workerport;
    private static String buildtools;
    private static String androidjar;

    // <------------------------------ Parâmetros de execução específica do Astor4Android ----------------------------------->

    private static String mode;
    private static String location;
    private static String java_compliance_level;
    private static String stopfirst;
    private static String flthreshold;
    private static String instrumentationfailing;

    public static String getCommandbase_run_astor4android() {
        return commandbase_run_astor4android;
    }

    public static String getCommandbase_run_astorworking() {
        return commandbase_run_astorworking;
    }

    public static String getAndroid_home() {
        return android_home;
    }

    public static void setAndroid_home(String android_home) {
        Astor4AndroidData.android_home = android_home;
    }

    public static String getJava_home() {
        return java_home;
    }

    public static void setJava_home(String java_home) {
        Astor4AndroidData.java_home = java_home;
    }

    public static String getAstorworker_directory() {
        return astorworker_directory;
    }

    public static void setAstorworker_directory(String astorworker_directory) {
        Astor4AndroidData.astorworker_directory = astorworker_directory;
    }

    public static String getAstor4android_directory() {
        return astor4android_directory;
    }

    public static void setAstor4android_directory(String astor4android_directory) {
        Astor4AndroidData.astor4android_directory = astor4android_directory;
    }

    public static String getHostip() {
        return hostip;
    }

    public static void setHostip(String hostip) {
        Astor4AndroidData.hostip = hostip;
    }

    public static String getHostport() {
        return hostport;
    }

    public static void setHostport(String hostport) {
        Astor4AndroidData.hostport = hostport;
    }

    public static String getWorkerip() {
        return workerip;
    }

    public static void setWorkerip(String workerip) {
        Astor4AndroidData.workerip = workerip;
    }

    public static String getWorkerport() {
        return workerport;
    }

    public static void setWorkerport(String workerport) {
        Astor4AndroidData.workerport = workerport;
    }

    public static String getBuildtools() {
        return buildtools;
    }

    public static void setBuildtools(String buildtools) {
        Astor4AndroidData.buildtools = buildtools;
    }

    public static String getAndroidjar() {
        return androidjar;
    }

    public static void setAndroidjar(String androidjar) {
        Astor4AndroidData.androidjar = androidjar;
    }

    public static String getMode() {
        return mode;
    }

    public static void setMode(String mode) {
        Astor4AndroidData.mode = mode;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Astor4AndroidData.location = location;
    }

    public static String getJava_compliance_level() {
        return java_compliance_level;
    }

    public static void setJava_compliance_level(String java_compliance_level) {
        Astor4AndroidData.java_compliance_level = java_compliance_level;
    }

    public static String getStopfirst() {
        return stopfirst;
    }

    public static void setStopfirst(String stopfirst) {
        Astor4AndroidData.stopfirst = stopfirst;
    }

    public static String getFlthreshold() {
        return flthreshold;
    }

    public static void setFlthreshold(String flthreshold) {
        Astor4AndroidData.flthreshold = flthreshold;
    }

    public static String getInstrumentationfailing() {
        return instrumentationfailing;
    }

    public static void setInstrumentationfailing(String instrumentationfailing) {
        Astor4AndroidData.instrumentationfailing = instrumentationfailing;
    }
}
