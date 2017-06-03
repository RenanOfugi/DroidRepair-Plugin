package com.ufg.i4soft.angelix_plugin.controller;

import java.util.ArrayList;

public class ManageAngelix {

    public void verifyInputs(ArrayList<String> args){

        boolean parametrosNaoVazios = true;

        for (String arg: args) {
            if (arg == null){
                parametrosNaoVazios = false;
            }
        }

        if (parametrosNaoVazios){
            executeAngelix();
        }
    }

    private void executeAngelix(){

    }
}
