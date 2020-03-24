package entity;

import java.util.ArrayList;

public class HandlingDataFile {
    public ArrayList<Work> works;
    public ArrayList<Work> GetDataWork() {
        ArrayList<Work> allWorks = new ArrayList<>();
        return allWorks;
    }

    public boolean SaveDataWork() {
        return true;
    }

    public boolean ExportData() {
        return true;
    }

    public void ImportData() {
    }
}

