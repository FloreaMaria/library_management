package main.domain;

public class Audit extends Entity<Integer>{

    private String action;
    private String timeStamp;

    public Audit(String action, String timeStamp) {
        this.action = action;
        this.timeStamp = timeStamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
