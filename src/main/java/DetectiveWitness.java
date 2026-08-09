import java.util.Scanner;

public class DetectiveWitness extends Witness{

    private Testimony testimony1;
    Scanner scanner=new Scanner(System.in);

    DetectiveWitness(String role, Testimony testimony1){
        super(role);
        this.testimony1=testimony1;

    }
    @Override
    void introduction(){
        Case.displayText("Detective Pie: The name's Smores Pie, lead homicide detective for the district!");
        Case.displayText("Detective Pie: Your Honor, before we begin, does anyone happen to have a spare doughnut?");
        Case.displayText("Detective Pie: Regardless, the facts of this case are already completely open and shut");

    }
    @Override
    void finish(int testimonyNumber){
        Case.displayText("Detective Pie: Oh man, the stress is making my blood sugar plummet. I... I need a sugar rush.");
    }

    @Override
    public String toString() {
        return getRole()+" "+testimony1;

    }

    public Testimony getTestimony1() {
        return testimony1;
    }
}
