package ar.edu.unju.edm.util;

public class ReviewConstructor {

    private Integer UID;
    private Integer PID;
    private Integer punctuation;

    public ReviewConstructor() {}

    public ReviewConstructor(Integer UID, Integer PID, Integer punctuation) {
        this.UID = UID;
        this.PID = PID;
        this.punctuation = punctuation;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public Integer getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Integer punctuation) {
        this.punctuation = punctuation;
    }

}