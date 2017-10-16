package com.ufg.i4soft.droidrepair.controller.Angelix;

import org.junit.Test;

public class FileManipulationTest {
    @Test
    public void deleteOldLines() throws Exception {

        String path = "/home/renan/Documentos/projetos/i4soft/testes";
        String arquivo_ler = "teste_leitura.txt";
        String arquivo_destino_trancricao = "puro.txt";

        FileManipulation fileManipulation = new FileManipulation();
        fileManipulation.deleteOldLines(path, arquivo_ler, path, arquivo_destino_trancricao);
    }

}