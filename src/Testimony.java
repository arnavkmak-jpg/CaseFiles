import java.util.ArrayList;

public class Testimony {
    private String name;
    private ArrayList<Statement> statements = new ArrayList<>();

   public Testimony(String name, ArrayList<Statement> statements){
        this.name=name;
        this.statements = statements;
    }

    @Override
    public String toString() {
        String result ="";
        for (Statement s:statements){
            result+=s+"\n";
        }
        return this.name+"\n"+result;

    }
}
