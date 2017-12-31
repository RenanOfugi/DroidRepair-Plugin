package com.ufg.i4soft.droidrepair.controller;

import org.junit.Test;

public class ExecuteShellThreadTest {

    @Test
    public void testExecuteShellThread() {

        String execute_avd_command = "export QEMU_AUDIO_DRV=none;" +
                "cd /home/renan/Android/Sdk/tools;" +
                "sudo -b ./emulator -avd testeAstor -no-skin -no-window -no-boot-anim";

        Thread thread = new Thread(new ExecuteShellThread(execute_avd_command, false , null));
        thread.start();

        try {

            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}