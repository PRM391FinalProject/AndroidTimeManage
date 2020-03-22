package entity;

public class Work {
    private String name;
    private double time;
    private String note;

    public Work(String name, double time, String note) {
        this.name = name;
        this.time = time;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Work() {
    }
}
