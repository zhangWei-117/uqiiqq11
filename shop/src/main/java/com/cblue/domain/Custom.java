package com.cblue.domain;

import java.util.Date;

public class Custom {
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Custom{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
