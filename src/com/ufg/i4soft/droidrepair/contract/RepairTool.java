package com.ufg.i4soft.droidrepair.contract;

import java.util.ArrayList;

public interface RepairTool {

    void startRepairTool();

    ArrayList<String> collectParameters(String path);

    void collectParameters();

    boolean verifyInputs(ArrayList<String> args);

    void executeRepairTool(ArrayList<String> args);

    void executeRepairTool();
}
