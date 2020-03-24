package entity;

import java.util.Date;

public class U1Work extends Work {
    private Date deadLine;
    private boolean notice;
private boolean rest;

    public boolean isRest() {
        return rest;
    }

    public void setRest(boolean rest) {
        this.rest = rest;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
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

    public U1Work(String name, double time, String note, int type, Date deadLine, boolean notice, boolean rest) {
        super(name, time, note, type);
        this.deadLine = deadLine;
        this.notice = notice;
        this.rest = rest;
    }
}
