package entity;

import java.sql.Date;
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
        allWorks.add(new U1Work("work",0,"something",1, new Date(2000),true,true));
        allWorks.add(new U1Work("work2",0,"Someone",1, new Date(2000),true,true));
        allWorks.add(new U1Work("work3",0,"somewhere",1, new Date(2000),true,true));
        allWorks.add(new U1Work("work4",0,"sometime",1, new Date(2000),true,true));
        allWorks.add(new U1Work("work5",0,"something",1, new Date(2000),true,true));
        allWorks.add(new U1Work("work6",0,"something",1, new Date(2000),true,true));
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

