package com.ufg.i4soft.droidrepair.controller.Angelix;

import com.ufg.i4soft.droidrepair.excecoes.ArquivoNaoEncontrado;
import com.ufg.i4soft.droidrepair.excecoes.ErroEscritaDados;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;

import java.io.*;

class FileManipulation {

    void deleteOldLines(String path_read, String file_patch, String path_writter, String file_generate) {

        File file = new File(path_read, file_patch);
        File file2 = new File(path_writter, file_generate);

        try {

            boolean created_file = file2.createNewFile();

            if (created_file){

                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);

                FileWriter file_writer = new FileWriter(file2);
                PrintWriter printWriter = new PrintWriter(file_writer);

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.toLowerCase().startsWith("@@") && !line.toLowerCase().startsWith("-")) {

                        printWriter.println(line);
                    }
                }

                fileReader.close();
                reader.close();
                printWriter.flush();
                printWriter.close();
            }

        } catch (FileNotFoundException e) {

            throw new ArquivoNaoEncontrado("O arquivo não foi encontrado");

        } catch (IOException e) {

            throw new ErroEscritaDados("Erro ao tentar escrever no arquivo");
        }
    }

    void searchFilePatch(String path) {

        File file = new File(path);
        File[] files = file.listFiles();

        FilterData filterData = new FilterData();

        if (files != null) {

            for (File currentfile : files) {

                if (currentfile.getPath().endsWith(".patch")) {

                    //String[] subpaths = filterData.splitPath(file.getna);
                    ProjectData.setName_filepatch(currentfile.getName());
                }
            }
        }
    }

    boolean deleteAllPatches(String path) {

        File file = new File(path);
        File[] files = file.listFiles();

        boolean delete = false;

        if (files != null) {

            for (File currentfile : files) {

                if (currentfile.getPath().endsWith(".patch")) {

                    delete = currentfile.delete();
                }
            }
        }

        return delete;
    }
}