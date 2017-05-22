package com.ufg.i4soft.angelix_plugin.view.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class runAngelix extends AnAction {

    public runAngelix(){
        super("testando plugin");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        testePlugin(event);
    }

    private void testePlugin(AnActionEvent event){

        Project project = event.getData(PlatformDataKeys.PROJECT);
        String txt= Messages.showInputDialog(project, "Qual o seu nome?", "Entre com seu nome", Messages.getInformationIcon());

        if (txt != null){
            Messages.showMessageDialog(project, "Olá " + txt + "!\n Aqui estou tentando testar um plugin simples.", "Information", Messages.getInformationIcon());
        } else{
            Messages.showMessageDialog(project, "Você não tem um nome...", "Error", Messages.getErrorIcon());
        }
    }
}
