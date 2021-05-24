package domain;

public class Audit extends Entity<Integer> {

    private String action;
    private String timeStamp;
    private static int auditId = 0;

    public Audit(String action, String timeStamp) {
        auditId += 1;
        this.action = action;
        this.timeStamp = timeStamp;
        this.setId(auditId);
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
