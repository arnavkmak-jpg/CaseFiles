import java.util.ArrayList;
import java.util.Scanner;

public class Case {
    Scanner scanner = new Scanner(System.in);
    private String caseName;
    private DetectiveWitness detective;
    private CulpritWitness culprit;
    ArrayList<Evidence> evidences;
    private int penalty;

    Case(String CaseName, DetectiveWitness detective, CulpritWitness culprit, ArrayList<Evidence> evidences){
        this.caseName=CaseName;
        this.detective=detective;
        this.culprit=culprit;
        this.evidences=evidences;
        this.penalty=3;

    }
    public void displayText(String text){
        System.out.println(text);
        scanner.nextLine();
    }

    public String getCaseName() {
        return caseName;
    }

    public CulpritWitness getCulprit() {
        return culprit;
    }

    public DetectiveWitness getDetective() {
        return detective;
    }

    public int getPenalty() {
        return penalty;
    }

    public void start(){
        //start game
    }
}
