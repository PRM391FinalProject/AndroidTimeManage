package entity;

public class Work {
    private String name;
    private long time;
    private String note;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public Work(String name, long time, String note, int type) {
        this.name = name;
        this.time = time;
        this.note = note;
        this.type = type;
    }


}
