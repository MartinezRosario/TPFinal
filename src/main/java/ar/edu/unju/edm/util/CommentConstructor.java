package ar.edu.unju.edm.util;

public class CommentConstructor {
 
    private Integer UID;
    private Integer PID;
    private String comment;
    public CommentConstructor() {}

    public CommentConstructor(Integer UID, Integer PID, String comment) {
        this.UID = UID;
        this.PID = PID;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}