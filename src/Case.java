import java.util.ArrayList;
import java.util.Locale;
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
    public void runTestimony(Testimony testimony, Witness witness, int testimonyNumber){
        ArrayList<Statement> currentStatements = testimony.getStatements();
        boolean contradictionCaught=false;
        int i = 0;
        while (penalty>0&&contradictionCaught==false){
            System.out.println(witness.getRole()+" "+testimony.getName());
            System.out.println(currentStatements.get(i));
                if (i==0){
                    System.out.println("""
                        P to press the statement
                        E to view evidence
                        I to view instructions
                        Enter to proceed to next statement
                        ....................................
                       
                        """);
                }
                else {
                    System.out.println("""
                        P to press the statement
                        E to view evidence
                        I to view instructions
                        Enter to proceed to next statement
                        B to back to previous statement
                        ..................................
                       
                        """);
                }
            String inp = scanner.nextLine().toLowerCase();
                Statement pressStatement = currentStatements.get(i);
                switch (inp){
                    case "p" -> {
                        if (i<currentStatements.size()-1){
                            System.out.println(pressStatement.getPress());
                            scanner.nextLine();
                            i++;
                        }
                        else {
                            System.out.println(pressStatement.getPress());
                            scanner.nextLine();
                            i=0;
                        }
                    }
                    case "e" -> {
                        System.out.println("Case Record:");
                        for (Evidence e:evidences){
                            System.out.println(e.getId()+". "+e.getName()+" : "+e.getDescription());
                        }
                        System.out.println("Pick an evidence to present: ");
                        int pick = scanner.nextInt();
                        scanner.nextLine();
                        Statement current = currentStatements.get(i);
                        if (pick==current.getMatchingEvidenceID()){
                            System.out.println("OBJECTION!");
                            contradictionCaught=true;
                        }
                        else {
                            System.out.println("Wrong evidence!");
                            penalty--;

                        }
                    }
                    case  "b" ->{
                        if (i==0){
                            System.out.println("Cannot go back from the first statement!");
                        }
                        else
                            i--;
                    }
                    case "" -> {
                        if (i<currentStatements.size()-1){
                            i++;
                        }
                        else{
                            i=0;
                        }
                    }
                    case "i" -> System.out.println("""
                            ----------HOW TO PLAY------------
                            
                            """);
                    default -> System.out.println("Invalid input! Please input P/E/B/Enter");

                }







        }


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
        displayText("The Courtroom is now in Session!");
        displayText("Context1");
        displayText("The court now calls upon the detective");
        getDetective().introduction();
        runTestimony(getDetective().getTestimony1(),getDetective(),1);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        getDetective().finish(1);
        displayText("Context2");
        displayText("The court now calls upon next witness");
        getCulprit().introduction();
        runTestimony(getCulprit().getTestimony1(),getCulprit(),1);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        getCulprit().finish(1);
        displayText("Context3");
        runTestimony(getCulprit().getTestimony2(),getCulprit(),2);
        if (penalty==0){
            System.out.println("The Judge has lost his patience! GAME OVER!");
            return;
        }
        getCulprit().finish(2);
        displayText("Context4");
        displayText("YOU WIN");
    }

}
