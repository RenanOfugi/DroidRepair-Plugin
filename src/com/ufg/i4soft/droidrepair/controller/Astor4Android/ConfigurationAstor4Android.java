package com.ufg.i4soft.droidrepair.controller.Astor4Android;

import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoEncontrado;
import com.ufg.i4soft.droidrepair.excecoes.ErroEscritaDados;
import com.ufg.i4soft.droidrepair.model.Astor4AndroidData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.io.*;
import java.util.Properties;

public class ConfigurationAstor4Android {

    public void createConfigurationFile() {

        Properties properties = new Properties();
        MainWindows windows = new MainWindows();

        String home = System.getProperty("user.home");

        properties.setProperty("androidHome", home + "/Android/Sdk");
        properties.setProperty("javaHome", windows.viewChooseFile("JAVA_HOME", "Selecione o diretório onde está o JDK"));
        properties.setProperty("astorworkerDirectory", home + "/droidrepair/astorworker");
        properties.setProperty("astor4androidDirectory", home + "/droidrepair/astor4android");
        properties.setProperty("hostip", "127.0.0.1");
        properties.setProperty("hostport", "6665");
        properties.setProperty("workerip", "127.0.0.1");
        properties.setProperty("workerport", "6666");
        properties.setProperty("buildtools", windows.viewChooseFile("BUILD_TOOLS", "Selecione a pasta com a versão do build-tools desejada. Localizado dentro da pasta Sdk"));
        properties.setProperty("androidjar", windows.viewChooseFile("ANDROID.JAR", "Selecione o android.jar correspondente ao emulador criado. Localizado dentro da pasta Sdk/platform"));
        properties.setProperty("mode", "statement");
        properties.setProperty("java_compliance_level", "8");
        properties.setProperty("stopfirst", "true");
        properties.setProperty("flthreshold", "0.9");

        try {

            File file = new File(home + "/droidrepair/config_astor4android.properties");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            properties.store(fileOutputStream, "Arquivo de configuração de Astor4Android");
            fileOutputStream.close();

        } catch (FileNotFoundException e) {

            throw new ArquivoNaoEncontrado("Arquivo não encontrado");

        } catch (IOException e) {

            throw new ErroEscritaDados("Falha na escrita do arquivo .properties");
        }
    }

    public void setVariablesWithFileProperties() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream(System.getProperty("user.home") + "/droidrepair/config_astor4android.properties"));

            Astor4AndroidData.setAndroid_home(properties.getProperty("androidHome"));
            Astor4AndroidData.setJava_home(properties.getProperty("javaHome"));
            Astor4AndroidData.setAstorworker_directory(properties.getProperty("astorworkerDirectory"));
            Astor4AndroidData.setAstor4android_directory(properties.getProperty("astor4androidDirectory"));
            Astor4AndroidData.setHostip(properties.getProperty("hostip"));
            Astor4AndroidData.setHostport(properties.getProperty("hostport"));
            Astor4AndroidData.setWorkerip(properties.getProperty("workerip"));
            Astor4AndroidData.setWorkerport(properties.getProperty("workerport"));
            Astor4AndroidData.setBuildtools(properties.getProperty("buildtools"));
            Astor4AndroidData.setAndroidjar(properties.getProperty("androidjar"));
            Astor4AndroidData.setMode(properties.getProperty("mode"));
            Astor4AndroidData.setJava_compliance_level(properties.getProperty("java_compliance_level"));
            Astor4AndroidData.setStopfirst(properties.getProperty("stopfirst"));
            Astor4AndroidData.setFlthreshold(properties.getProperty("flthreshold"));

        } catch (IOException e) {

            throw new ErroEscritaDados("Erro ao ler os dados de .properties");
        }
    }
}
