//class for statements provided by the witness
public class Statement {
   private String text;
   private boolean isContradictable;
   private int matchingEvidenceID;
   private String press;

    public Statement(String text, boolean isContradictable, int matchingEvidenceID, String press){
        this.text=text;
        this.isContradictable=isContradictable;
        this.matchingEvidenceID=matchingEvidenceID;
        this.press=press;

    }

    @Override
    public String toString() {
        return this.text;
    }

    public String getText() {
        return text;
    }

    public boolean isContradictable() {
        return isContradictable;
    }

    public int getMatchingEvidenceID() {
        return matchingEvidenceID;
    }

    public String getPress() {
        return press;
    }
}
