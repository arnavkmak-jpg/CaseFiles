public class Statement {
   private String text;
   private boolean isContradictable;
   private int matchingEvidenceID;

    public Statement(String text, boolean isContradictable, int matchingEvidenceID){
        this.text=text;
        this.isContradictable=isContradictable;
        this.matchingEvidenceID=matchingEvidenceID;

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
}
