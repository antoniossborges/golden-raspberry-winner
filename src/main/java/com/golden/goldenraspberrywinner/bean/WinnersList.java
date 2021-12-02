package com.golden.goldenraspberrywinner.bean;

import java.util.ArrayList;
import java.util.List;

public class WinnersList {

    private List<Winner> min;

    private List<Winner> max;

    public List<Winner> getMin() {
        if(min == null) min =  new ArrayList<>();
        return min;
    }

    public void setMin(List<Winner> min) {
        this.min = min;
    }

    public List<Winner> getMax() {
        if(max == null) max = new ArrayList<>();
        return max;
    }

    public void setMax(List<Winner> max) {
        this.max = max;
    }
}
