package com.ufg.i4soft.droidrepair.controller.Angelix;

import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoEncontrado;
import com.ufg.i4soft.droidrepair.excecoes.ErroEscritaDados;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;

import java.io.*;

public class FileManipulation {

    public void deleteOldLines() {

        File file = new File(ProjectData.getPath_of_patch() + ProjectData.getName_filepatch());
        File file2 = new File(ProjectData.getPath_of_patch() + "patchPuro.txt");

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(file2);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.startsWith("-") || !line.startsWith("@@")) {
                    writer.write(line);
                }
            }

        } catch (FileNotFoundException e) {

            throw new ArquivoNaoEncontrado("O arquivo não foi encontrado");

        } catch (IOException e) {

            throw new ErroEscritaDados("Erro ao tentar escrever no arquivo");
        }
    }

    public void searchFilePatch() {

        File file = new File(ProjectData.getPath_of_patch());
        File[] files = file.listFiles();

        FilterData filterData = new FilterData();

        if (files != null) {

            for (File currentfile : files) {

                if (currentfile.getPath().endsWith(".patch")) {

                    String[] subpaths = filterData.splitPath(file.getPath());
                    ProjectData.setName_filepatch(subpaths[1]);
                }
            }
        }
    }

    void deleteAllPatches() {

        File file = new File(ProjectData.getPath_of_patch());
        File[] files = file.listFiles();

        if (files != null) {

            for (File currentfile : files) {

                if (currentfile.getPath().endsWith(".patch")) {

                   currentfile.delete();
                }
            }
        }

    }
}