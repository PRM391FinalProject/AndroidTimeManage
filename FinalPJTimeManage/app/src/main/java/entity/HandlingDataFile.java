package entity;

import java.util.ArrayList;

public class HandlingDataFile {
    ////Local variable
    private ArrayList<Work> works;

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }


    public ArrayList<Work> GetDataWork() {
        ArrayList<Work> allWorks = new ArrayList<>();
        allWorks.add(new Work("work",11,"something",1));
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

