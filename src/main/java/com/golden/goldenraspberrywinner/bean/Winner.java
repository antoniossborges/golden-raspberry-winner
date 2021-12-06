package com.golden.goldenraspberrywinner.bean;

public class Winner {

    private String producerName;

    private Integer previousWin;

    private Integer followingWin;

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Integer getInterval() {
        if (this.getFollowingWin() == null) return 0;
        else return this.getFollowingWin() - this.getPreviousWin();
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }
}
