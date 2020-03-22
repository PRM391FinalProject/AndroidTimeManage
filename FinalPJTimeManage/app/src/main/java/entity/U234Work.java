package entity;

import java.util.ArrayList;

public class U234Work extends Work{
    private boolean T2;
    private boolean T3;
    private boolean T4;
    private boolean T5;
    private boolean T6;
    private boolean T7;
    private boolean CN;

    public U234Work() {
    }

    public U234Work(String name, double time, String note, boolean t2, boolean t3, boolean t4, boolean t5, boolean t6, boolean t7, boolean CN) {
        super(name, time, note);
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        T7 = t7;
        this.CN = CN;
    }

    public boolean isT2() {
        return T2;
    }

    public void setT2(boolean t2) {
        T2 = t2;
    }

    public boolean isT3() {
        return T3;
    }

    public void setT3(boolean t3) {
        T3 = t3;
    }

    public boolean isT4() {
        return T4;
    }

    public void setT4(boolean t4) {
        T4 = t4;
    }

    public boolean isT5() {
        return T5;
    }

    public void setT5(boolean t5) {
        T5 = t5;
    }

    public boolean isT6() {
        return T6;
    }

    public void setT6(boolean t6) {
        T6 = t6;
    }

    public boolean isT7() {
        return T7;
    }

    public void setT7(boolean t7) {
        T7 = t7;
    }

    public boolean isCN() {
        return CN;
    }

    public void setCN(boolean CN) {
        this.CN = CN;
    }
}
