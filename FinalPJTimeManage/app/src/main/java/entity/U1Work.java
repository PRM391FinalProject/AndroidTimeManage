package entity;

import java.util.Date;

public class U1Work extends Work {
    private String deadLine;
    private boolean notice;
private boolean rest;

    public boolean isRest() {
        return rest;
    }

    public void setRest(boolean rest) {
        this.rest = rest;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isNotice() {
        return notice;
    }

    public void setNotice(boolean notice) {
        this.notice = notice;
    }

    public U1Work() {
    }

    public U1Work(long time, String note, int type, String deadLine, boolean notice, boolean rest) {
        super(time, note, type);
        this.deadLine = deadLine;
        this.notice = notice;
        this.rest = rest;
    }

    public U1Work(String name, long time, String note, int type, String deadLine, boolean notice, boolean rest) {
        super(name, time, note, type);
        this.deadLine = deadLine;
        this.notice = notice;
        this.rest = rest;
    }
}
